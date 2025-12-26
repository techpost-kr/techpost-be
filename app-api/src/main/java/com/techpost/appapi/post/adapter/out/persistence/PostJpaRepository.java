package com.techpost.appapi.post.adapter.out.persistence;

import com.techpost.appapi.post.domain.enums.Publisher;
import com.techpost.appapi.post.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    List<Post> findByPublisher(Publisher publisher);
}

