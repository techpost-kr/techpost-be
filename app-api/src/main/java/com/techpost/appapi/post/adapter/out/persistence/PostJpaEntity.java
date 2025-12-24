package com.techpost.appapi.post.adapter.out.persistence;


import com.techpost.appapi.common.constant.TableConstants;
import com.techpost.appapi.common.entity.BaseEntity;
import com.techpost.appapi.post.domain.enums.Publisher;
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
public class PostJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postSeq;

    @Column
    @Enumerated(EnumType.STRING)
    private Publisher publisher; // 발행처

    @Column
    private String title;

    @Column
    private String url;

    @Column
    private LocalDateTime publishedDateTime;

    public static PostJpaEntity of(Publisher publisher,
                                   String title,
                                   String url,
                                   LocalDateTime publishedDateTime) {
        return PostJpaEntity.builder()
                .publisher(publisher)
                .title(title)
                .url(url)
                .publishedDateTime(publishedDateTime)
                .build();
    }
}
