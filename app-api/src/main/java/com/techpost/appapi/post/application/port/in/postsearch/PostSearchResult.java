package com.techpost.appapi.post.application.port.in.postsearch;

import com.techpost.appapi.post.domain.enums.Publisher;
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

}

