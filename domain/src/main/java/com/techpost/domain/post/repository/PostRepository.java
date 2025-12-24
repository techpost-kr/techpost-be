package com.techpost.domain.post.repository;

import com.techpost.domain.post.entity.Post;
import com.techpost.domain.post.enums.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostQueryRepository {

    List<Post> findByPublisher(Publisher publisher);
}
