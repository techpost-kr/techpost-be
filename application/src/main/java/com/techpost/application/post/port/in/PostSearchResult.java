package com.techpost.application.post.port.in;

import com.techpost.domain.post.model.Publisher;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 게시물 검색 결과
 */
@Getter
@AllArgsConstructor
public class PostSearchResult {

    private Long postId;
    private Publisher publisher;
    private String title;
    private String url;
    private LocalDateTime publishedAt;

    public static PostSearchResult of(
            Long postId,
            Publisher publisher,
            String title,
            String url,
            LocalDateTime publishedAt) {
        return new PostSearchResult(postId, publisher, title, url, publishedAt);
    }
}

