package com.techpost.appapi.post.adapter.in.web;

import com.techpost.appapi.common.dto.response.PageResponse;
import com.techpost.appapi.post.adapter.in.web.dto.request.PostRequest;
import com.techpost.common.response.ApiResponse;
import com.techpost.appapi.post.domain.dto.result.PostResult;
import com.techpost.appapi.post.application.PostQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostQueryService postQueryService;

    @GetMapping
    public ApiResponse<PageResponse<PostResult>> searchPosts(@Valid PostRequest request) {
        Page<PostResult> response = postQueryService.searchPosts(request.toPostCriteria(), request.toPageable());
        return ApiResponse.success(PageResponse.of(response));
    }

}
