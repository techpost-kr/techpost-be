package com.techpost.appapi.post.adapter.out.persistence.repository;

import com.techpost.domain.post.dto.criteria.PostCriteria;
import com.techpost.domain.post.dto.result.PostResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostQueryRepository {

    Page<PostResult> searchPosts(PostCriteria criteria, Pageable pageable);

}
