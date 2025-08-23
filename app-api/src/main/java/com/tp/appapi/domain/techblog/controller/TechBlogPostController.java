package com.tp.appapi.domain.techblog.controller;

import com.tp.appapi.common.dto.response.PageResponse;
import com.tp.appapi.domain.techblog.dto.request.TechBlogPostRequest;
import com.tp.common.response.ApiResponse;
import com.tp.domain.techblog.dto.result.TechBlogPostResult;
import com.tp.domain.techblog.service.TechBlogPostQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tech-blog-post")
public class TechBlogPostController {

    private final TechBlogPostQueryService techBlogPostService;

    @GetMapping
    public ApiResponse<PageResponse<TechBlogPostResult>> searchTechBlogPosts(@Valid TechBlogPostRequest request) {
        Page<TechBlogPostResult> response = techBlogPostService.searchTechBlogPosts(request.toTechBlogPostCriteria(), request.toPageable());
        return ApiResponse.success(PageResponse.of(response));
    }

}
