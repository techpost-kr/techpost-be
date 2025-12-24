package com.techpost.domain.techblog.service;

import com.techpost.domain.techblog.dto.criteria.TechBlogPostCriteria;
import com.techpost.domain.techblog.dto.result.TechBlogPostResult;
import com.techpost.domain.techblog.repository.TechBlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechBlogPostQueryService {

    private final TechBlogPostRepository techBlogPostRepository;

    public Page<TechBlogPostResult> searchTechBlogPosts(TechBlogPostCriteria criteria, Pageable pageable) {
        return techBlogPostRepository.searchTechBlogPosts(criteria, pageable);
    }
}
