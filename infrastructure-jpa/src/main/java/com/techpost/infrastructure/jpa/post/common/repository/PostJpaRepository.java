package com.techpost.infrastructure.jpa.post.common.repository;

import com.techpost.domain.post.model.Publisher;
import com.techpost.infrastructure.jpa.post.common.entity.PostJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Post JPA Repository
 */
public interface PostJpaRepository extends JpaRepository<PostJpaEntity, Long> {

    List<PostJpaEntity> findByPublisher(Publisher publisher);
}

