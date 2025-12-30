package com.techpost.domain.post.model;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Post 도메인 모델
 * - 순수한 비즈니스 로직만 포함
 * - 인프라 기술(JPA 등)에 의존하지 않음
 */
@Getter
public class Post {

    private final PostId postId;

    private final Publisher publisher; // 발행처

    private final String title;

    private final String url;

    private final LocalDateTime publishedAt;

    private Post(Publisher publisher,
                 String title,
                 String url,
                 LocalDateTime publishedAt) {
        this.postId = PostId.of();
        this.publisher = publisher;
        this.title = title;
        this.url = url;
        this.publishedAt = publishedAt;
    }

    public static Post of(Publisher publisher,
                          String title,
                          String url,
                          LocalDateTime publishedAt) {
        return new Post(publisher, title, url, publishedAt);
    }
}
