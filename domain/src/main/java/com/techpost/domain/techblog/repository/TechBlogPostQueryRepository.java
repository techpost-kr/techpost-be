package com.techpost.domain.techblog.repository;

import com.techpost.domain.techblog.dto.criteria.TechBlogPostCriteria;
import com.techpost.domain.techblog.dto.result.TechBlogPostResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TechBlogPostQueryRepository {

    Page<TechBlogPostResult> searchTechBlogPosts(TechBlogPostCriteria criteria, Pageable pageable);

}
