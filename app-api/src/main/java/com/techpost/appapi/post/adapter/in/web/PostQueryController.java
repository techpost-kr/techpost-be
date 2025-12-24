package com.techpost.appapi.post.adapter.in.web;

import com.techpost.appapi.common.dto.page.PageResponse;
import com.techpost.appapi.common.dto.page.PageResult;
import com.techpost.appapi.post.adapter.in.web.dto.PostSearchRequest;
import com.techpost.appapi.post.adapter.in.web.dto.PostSearchResponse;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchQuery;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchResult;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchUseCase;
import com.techpost.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 게시물 조회 컨트롤러 (인바운드 어댑터)
 *
 * 헥사고날 아키텍처:
 * - SearchPostUseCase(인터페이스)에 의존 ✅
 * - Service 구현체를 직접 알지 못함 ✅
 * - 의존성 역전 원칙(DIP) 준수 ✅
 *
 * 데이터 흐름:
 * Request → Query → UseCase → Result → Response
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostQueryController {

    private final PostSearchUseCase postSearchUseCase;

    /**
     * 게시물 검색 API
     *
     * @param request 검색 요청 (Web Layer DTO)
     * @return 검색 결과 응답
     */
    @GetMapping
    public ApiResponse<PageResponse<PostSearchResponse>> searchPosts(@Valid PostSearchRequest request) {
        log.info("게시물 검색 API 호출: query={}", request.getQuery());

        // 1. Request -> Query 변환 (Web DTO -> Application Query)
        PostSearchQuery query = request.toPostSearchQuery();

        // 2. UseCase 호출 (인터페이스를 통한 호출)
        PageResult<PostSearchResult> result = postSearchUseCase.search(query);

        // 3. Result -> Response 변환 (Application Result -> Web DTO)
        List<PostSearchResponse> searchResponses = result.content().stream()
                .map(PostSearchResponse::from)
                .collect(Collectors.toList());

        PageResponse<PostSearchResponse> pageResponse = PageResponse.of(
                searchResponses,
                result.totalElements(),
                result.totalPages(),
                result.currentPage()
        );

        // 4. API 응답 반환
        log.info("게시물 검색 API 완료: 총 {} 건", result.totalElements());
        return ApiResponse.success(pageResponse);
    }

}
