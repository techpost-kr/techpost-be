package com.techpost.appbatch.domain.techblogscrap.job;

import com.techpost.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.techpost.appbatch.domain.techblogscrap.scraper.TechBlogScraper;
import com.techpost.appbatch.domain.techblogscrap.scraper.TechBlogScraperFactory;
import com.techpost.domain.slack.entity.repository.SlackWebhookRepository;
import com.techpost.domain.slack.enums.SlackWebhookEnum;
import com.techpost.domain.post.entity.Post;
import com.techpost.domain.post.enums.Publisher;
import com.techpost.domain.post.repository.PostRepository;
import com.techpost.slack.webhook.SlackWebhookClient;
import com.techpost.slack.webhook.dto.SlackWebhookRequest;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TechBlogScrapProcessor implements ItemProcessor<TechBlogScrapEnum, List<Post>> {

    private final TechBlogScraperFactory techBlogScraperFactory;

    private final PostRepository postRepository;

    private final SlackWebhookRepository slackWebhookRepository;

    @Override
    public List<Post> process(@Nonnull TechBlogScrapEnum techBlogScrapEnum) {

        log.info("start scrap process");

        List<Post> posts = scrap(techBlogScrapEnum);

        sendSlackWebhook(posts);

        return posts;

    }

    private List<Post> scrap(TechBlogScrapEnum techBlogScrapEnum) {
        TechBlogScraper techBlogScraper = techBlogScraperFactory.getTechBlogScraper(techBlogScrapEnum);

        List<Post> posts = techBlogScraper.scrap();

        if (CollectionUtils.isEmpty(posts)) return Collections.emptyList();

        Set<String> urlSet = getUrlSet(techBlogScrapEnum.getPublisher());

        return posts.stream()
                .filter(techBlogPost -> !urlSet.contains(techBlogPost.getUrl()))
                .collect(Collectors.toList());
    }

    // url 기준으로 동일 데이터에 대해서 필터처리
    private Set<String> getUrlSet(Publisher publisher) {
        return postRepository.findByTechBlogEnum(publisher).stream()
                .map(Post::getUrl)
                .collect(Collectors.toSet());
    }

    private void sendSlackWebhook(List<Post> posts) {
        String slackWebhookUrl = this.getUrl();
        for (Post techBlogPost : posts) {
            try {
                SlackWebhookClient.postMessage(slackWebhookUrl, this.createWebhookRequest(techBlogPost));
            } catch (RuntimeException e) {
                // 예외 발생 시 로깅
                log.error("Failed to send message for post: {}", techBlogPost.getTitle(), e);
            }
        }
    }

    private String getUrl() {
        return slackWebhookRepository.findBySlackWebhookEnum(SlackWebhookEnum.TECH_BLOG_SCRAP)
                .orElseThrow(RuntimeException::new)
                .getUrl();
    }

    private SlackWebhookRequest createWebhookRequest(Post techBlogPost) {
        String text = String.format("%s%n%s", techBlogPost.getTitle(), techBlogPost.getUrl());
        return SlackWebhookRequest.of(text);
    }

}
