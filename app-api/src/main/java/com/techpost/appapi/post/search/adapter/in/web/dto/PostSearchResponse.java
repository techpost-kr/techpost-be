package com.techpost.appapi.post.search.adapter.in.web.dto;

import com.techpost.appapi.post.search.application.port.in.PostSearchResult;

import java.time.LocalDateTime;

public record PostSearchResponse(
        Long postId,
        String publisher,
        String title,
        String url,
        LocalDateTime publishedAt
) {
    public static PostSearchResponse from(PostSearchResult result) {
        return new PostSearchResponse(
                result.getPostId(),
                result.getPublisher().getName(),
                result.getTitle(),
                result.getUrl(),
                result.getPublishedAt());
    }
}

