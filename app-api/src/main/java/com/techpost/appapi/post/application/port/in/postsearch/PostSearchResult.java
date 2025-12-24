package com.techpost.appapi.post.application.port.in.postsearch;

import com.querydsl.core.annotations.QueryProjection;
import com.techpost.appapi.post.domain.enums.Publisher;
import com.techpost.appapi.post.domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 게시물 검색 결과 (PostSearchUseCase 출력)
 *
 * UseCase와 함께 위치:
 * - PostSearchUseCase의 출력 계약
 * - 유스케이스별로 그룹핑
 */
@Getter
@AllArgsConstructor
public class PostSearchResult {

    private Long postId;
    private Publisher publisher;
    private String title;
    private String url;
    private LocalDateTime publishedAt;

    @QueryProjection
    public PostSearchResult(Post post) {
        this.postId = post.getPostId();
        this.publisher = post.getPublisher();
        this.title = post.getTitle();
        this.url = post.getUrl();
        this.publishedAt = post.getPublishedAt();
    }
}

