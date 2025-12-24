package com.techpost.domain.post.entity;


import com.techpost.domain.common.constant.TableConstants;
import com.techpost.domain.common.entity.BaseEntity;
import com.techpost.domain.post.enums.Publisher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = TableConstants.POST)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postseq;

    @Column
    @Enumerated(EnumType.STRING)
    private Publisher publisher; // 발행처

    @Column
    private String title;

    @Column
    private String url;

    @Column
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
