package com.tp.domain.techblog.dto.result;

import com.querydsl.core.annotations.QueryProjection;
import com.tp.domain.techblog.entity.TechBlogPost;
import com.tp.domain.techblog.enums.TechBlogEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TechBlogPostResult {

    private Long techBlogPostSeq;
    private TechBlogEnum techBlogEnum;
    private String title;
    private String url;
    private LocalDateTime publishedDateTime;

    @QueryProjection
    public TechBlogPostResult(TechBlogPost techBlogPost) {
        this.techBlogPostSeq = techBlogPost.getTechBlogPostSeq();
        this.techBlogEnum = techBlogPost.getTechBlogEnum();
        this.title = techBlogPost.getTitle();
        this.url = techBlogPost.getUrl();
        this.publishedDateTime = techBlogPost.getPublishedDateTime();
    }
}
