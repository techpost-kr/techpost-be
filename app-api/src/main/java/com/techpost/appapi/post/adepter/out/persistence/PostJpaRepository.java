package com.techpost.appapi.post.adepter.out.persistence;

import com.techpost.appapi.post.adepter.out.persistence.entity.PostJpaEntity;
import com.techpost.appapi.post.domain.enums.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<PostJpaEntity, Long> {

    List<PostJpaEntity> findByPublisher(Publisher publisher);
}

