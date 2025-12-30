package com.techpost.appbatch.post.scrap.step;

import com.techpost.appbatch.post.scrap.enums.PublisherScrapEnum;
import com.techpost.appbatch.post.scrap.scraper.PostScraper;
import com.techpost.appbatch.post.scrap.scraper.PostScraperFactory;
import com.techpost.application.post.port.out.PostSearchPort;
import com.techpost.domain.post.model.Post;
import com.techpost.domain.post.model.Publisher;
import com.techpost.slack.webhook.SlackWebhookClient;
import com.techpost.slack.webhook.dto.SlackWebhookRequest;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
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
    private final PostSearchPort postSearchPort;

    @Value("${slack.webhook.url}")
    private String slackWebhookUrl;

    @Override
    public List<Post> process(@Nonnull PublisherScrapEnum publisherScrapEnum) {

        log.info("start scrap process");

        List<Post> posts = scrap(publisherScrapEnum);

        // Slack 알림 전송
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
        return postSearchPort.searchByPublisher(publisher).stream()
                .map(Post::getUrl)
                .collect(Collectors.toSet());
    }

    private void sendSlackWebhook(List<Post> posts) {
        for (Post post : posts) {
            try {
                log.info("New post scraped: {} - {}", post.getTitle(), post.getUrl());
                SlackWebhookClient.postMessage(slackWebhookUrl, createWebhookRequest(post));
            } catch (RuntimeException e) {
                log.error("Failed to send message for post: {}", post.getTitle(), e);
            }
        }
    }

    private SlackWebhookRequest createWebhookRequest(Post post) {
        String text = String.format("%s%n%s", post.getTitle(), post.getUrl());
        return SlackWebhookRequest.of(text);
    }

}
