package com.techpost.domain.post.dto.result;

import com.querydsl.core.annotations.QueryProjection;
import com.techpost.domain.post.entity.Post;
import com.techpost.domain.post.enums.Publisher;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResult {

    private Long postSeq;
    private Publisher publisher;
    private String title;
    private String url;
    private LocalDateTime publishedDateTime;

    @QueryProjection
    public PostResult(Post post) {
        this.postSeq = post.getPostSeq();
        this.publisher = post.getPublisher();
        this.title = post.getTitle();
        this.url = post.getUrl();
        this.publishedDateTime = post.getPublishedDateTime();
    }
}
