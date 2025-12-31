package com.techpost.appbatch.post.scrap.dto;

import com.techpost.application.post.save.port.in.PostSaveCommand;
import com.techpost.domain.post.model.Publisher;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostScrapDto {

    private final Publisher publisher; // 발행처

    private final String title;

    private final String url;

    private final LocalDateTime publishedAt;

    private PostScrapDto(Publisher publisher,
                         String title,
                         String url,
                         LocalDateTime publishedAt) {
        this.publisher = publisher;
        this.title = title;
        this.url = url;
        this.publishedAt = publishedAt;
    }

    public static PostScrapDto of(Publisher publisher,
                                  String title,
                                  String url,
                                  LocalDateTime publishedAt) {
        return new PostScrapDto(publisher, title, url, publishedAt);
    }

    public static PostSaveCommand toCommand(PostScrapDto post) {
        return new PostSaveCommand(
                post.getPublisher(),
                post.getTitle(),
                post.getUrl(),
                post.getPublishedAt()
        );
    }
}
