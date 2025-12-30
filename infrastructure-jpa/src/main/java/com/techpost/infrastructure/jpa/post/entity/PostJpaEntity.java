package com.techpost.infrastructure.jpa.post.entity;

import com.techpost.domain.post.model.Publisher;
import com.techpost.infrastructure.jpa.common.constant.TableConstants;
import com.techpost.infrastructure.jpa.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Post JPA 엔티티
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = TableConstants.POST)
public class PostJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    @Enumerated(EnumType.STRING)
    private Publisher publisher; // 발행처

    @Column
    private String title;

    @Column
    private String url;

    @Column
    private LocalDateTime publishedAt;

    public static PostJpaEntity of(Publisher publisher,
                                   String title,
                                   String url,
                                   LocalDateTime publishedAt) {
        return PostJpaEntity.builder()
                .publisher(publisher)
                .title(title)
                .url(url)
                .publishedAt(publishedAt)
                .build();
    }
}

