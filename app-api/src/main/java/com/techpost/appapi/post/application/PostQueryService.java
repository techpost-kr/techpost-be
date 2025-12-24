package com.techpost.appapi.post.application;

import com.techpost.domain.post.dto.criteria.PostCriteria;
import com.techpost.domain.post.dto.result.PostResult;
import com.techpost.appapi.post.adapter.out.persistence.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostQueryService {

    private final PostRepository postRepository;

    public Page<PostResult> searchPosts(PostCriteria criteria, Pageable pageable) {
        return postRepository.searchPosts(criteria, pageable);
    }
}
