package com.techpost.appapi.post.search.adapter.in.web;

import com.techpost.appapi.common.page.adapter.PageResponse;
import com.techpost.appapi.post.search.adapter.in.web.dto.PostSearchRequest;
import com.techpost.appapi.post.search.adapter.in.web.dto.PostSearchResponse;
import com.techpost.common.response.ApiResponse;
import com.techpost.application.common.page.PageResult;
import com.techpost.application.post.search.port.in.PostSearchQuery;
import com.techpost.application.post.search.port.in.PostSearchResult;
import com.techpost.application.post.search.port.in.PostSearchUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 게시물 조회 컨트롤러 (인바운드 어댑터)
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostSearchController {

    private final PostSearchUseCase postSearchUseCase;

    /**
     * 게시물 검색 API
     *
     * @param request 검색 요청
     * @return 검색 결과 응답
     */
    @GetMapping
    public ApiResponse<PageResponse<PostSearchResponse>> search(@Valid PostSearchRequest request) {

        // 1. Request -> Query 변환
        PostSearchQuery query = request.toPostSearchQuery();

        // 2. UseCase 호출
        PageResult<PostSearchResult> result = postSearchUseCase.search(query);

        // 3. Result -> Response 변환
        PageResponse<PostSearchResponse> response = PageResponse.from(result, PostSearchResponse::from);

        // 4. API 응답 반환
        return ApiResponse.success(response);
    }

}
