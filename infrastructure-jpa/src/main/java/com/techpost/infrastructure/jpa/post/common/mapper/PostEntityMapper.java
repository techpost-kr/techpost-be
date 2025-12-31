package com.techpost.infrastructure.jpa.post.common.mapper;

import com.techpost.application.post.search.port.in.PostSearchResult;
import com.techpost.domain.post.model.Post;
import com.techpost.infrastructure.jpa.post.common.entity.PostJpaEntity;
import org.springframework.stereotype.Component;

/**
 * Post 도메인 모델 <-> JPA 엔티티 변환 매퍼
 */
@Component
public class PostEntityMapper {

    /**
     * 도메인 모델 -> JPA 엔티티
     */
    public PostJpaEntity toEntity(Post post) {
        return PostJpaEntity.of(
                post.getPublisher(),
                post.getTitle(),
                post.getUrl(),
                post.getPublishedAt()
        );
    }

    /**
     * JPA 엔티티 -> 도메인 모델
     */
    public Post toDomain(PostJpaEntity entity) {
        return Post.of(
                entity.getPostId(),
                entity.getPublisher(),
                entity.getTitle(),
                entity.getUrl(),
                entity.getPublishedAt()
        );
    }

    public PostSearchResult toSearchResult(PostJpaEntity entity) {
        return PostSearchResult.of(
                entity.getPostId(),
                entity.getPublisher(),
                entity.getTitle(),
                entity.getUrl(),
                entity.getPublishedAt()
        );
    }
}

