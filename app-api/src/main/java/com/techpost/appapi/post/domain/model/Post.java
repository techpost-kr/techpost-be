package com.techpost.appapi.post.domain.model;


import com.techpost.appapi.post.domain.enums.Publisher;
import lombok.Getter;

import java.time.LocalDateTime;

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
