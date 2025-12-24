package com.techpost.appapi.post.adapter.in.web.dto;

import com.techpost.appapi.post.application.port.in.postsearch.PostSearchResult;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostSearchResponse {
    private final Long postId;
    private final String publisher;
    private final String title;
    private final String url;
    private final LocalDateTime publishedAt;
    private final LocalDateTime createdAt;

    public static PostSearchResponse from(PostSearchResult result) {
        return PostSearchResponse.builder()
                .postId(result.getPostId())
                .publisher(result.getPublisher().getName())
                .title(result.getTitle())
                .url(result.getUrl())
                .publishedAt(result.getPublishedAt())
                .build();
    }
}

