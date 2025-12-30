package com.techpost.appbatch.post.scrap.step;

import com.techpost.appbatch.post.scrap.enums.PublisherScrapEnum;
import com.techpost.appbatch.post.scrap.scraper.PostScraper;
import com.techpost.appbatch.post.scrap.scraper.PostScraperFactory;
import com.techpost.common.slack.entity.repository.SlackWebhookRepository;
import com.techpost.common.slack.enums.SlackWebhookEnum;
import com.techpost.common.post.entity.Post;
import com.techpost.common.post.enums.Publisher;
import com.techpost.common.post.repository.PostRepository;
import com.techpost.slack.webhook.SlackWebhookClient;
import com.techpost.slack.webhook.dto.SlackWebhookRequest;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostScrapProcessor implements ItemProcessor<PublisherScrapEnum, List<Post>> {

    private final PostScraperFactory postScraperFactory;

    private final PostRepository postRepository;

    private final SlackWebhookRepository slackWebhookRepository;

    @Override
    public List<Post> process(@Nonnull PublisherScrapEnum publisherScrapEnum) {

        log.info("start scrap process");

        List<Post> posts = scrap(publisherScrapEnum);

        sendSlackWebhook(posts);

        return posts;

    }

    private List<Post> scrap(PublisherScrapEnum publisherScrapEnum) {
        PostScraper postScraper = postScraperFactory.getPostScraper(publisherScrapEnum);

        List<Post> posts = postScraper.scrap();

        if (CollectionUtils.isEmpty(posts)) return Collections.emptyList();

        Set<String> urlSet = getUrlSet(publisherScrapEnum.getPublisher());

        return posts.stream()
                .filter(post -> !urlSet.contains(post.getUrl()))
                .collect(Collectors.toList());
    }

    // url 기준으로 동일 데이터에 대해서 필터처리
    private Set<String> getUrlSet(Publisher publisher) {
        return postRepository.findByPublisher(publisher).stream()
                .map(Post::getUrl)
                .collect(Collectors.toSet());
    }

    private void sendSlackWebhook(List<Post> posts) {
        String slackWebhookUrl = this.getUrl();
        for (Post post : posts) {
            try {
                SlackWebhookClient.postMessage(slackWebhookUrl, this.createWebhookRequest(post));
            } catch (RuntimeException e) {
                // 예외 발생 시 로깅
                log.error("Failed to send message for post: {}", post.getTitle(), e);
            }
        }
    }

    private String getUrl() {
        return slackWebhookRepository.findBySlackWebhookEnum(SlackWebhookEnum.POST_SCRAP)
                .orElseThrow(RuntimeException::new)
                .getUrl();
    }

    private SlackWebhookRequest createWebhookRequest(Post post) {
        String text = String.format("%s%n%s", post.getTitle(), post.getUrl());
        return SlackWebhookRequest.of(text);
    }

}
