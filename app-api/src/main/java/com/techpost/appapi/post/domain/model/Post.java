package com.techpost.appapi.post.domain.model;


import com.techpost.appapi.post.domain.enums.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    private Long postSeq;

    private Publisher publisher; // 발행처

    private String title;

    private String url;

    private LocalDateTime publishedDateTime;

    public static Post of(Publisher publisher,
                          String title,
                          String url,
                          LocalDateTime publishedDateTime) {
        return Post.builder()
                .publisher(publisher)
                .title(title)
                .url(url)
                .publishedDateTime(publishedDateTime)
                .build();
    }
}
