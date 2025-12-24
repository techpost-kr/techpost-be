package com.techpost.appapi.post.application.port.out.postsearch;

import com.techpost.appapi.common.dto.page.PageResult;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchQuery;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchResult;

/**
 * 게시물 조회 포트 (아웃바운드 포트)
 * - Application Layer에서 정의
 * - Adapter Layer에서 구현
 *
 * 헥사고날 아키텍처:
 * Application -> Port (인터페이스) <- Adapter (구현체)
 *
 * 프레임워크 독립성:
 * - Spring Pageable 대신 PostSearchQuery 사용 ✅
 * - Spring Page 대신 PageResult 사용 ✅
 *
 * 입력 단순화:
 * - PostSearchQuery에 검색 조건과 페이징 정보 모두 포함 ✅
 * - 별도의 PageRequest 불필요 ✅
 */
public interface PostSearchPort {

    /**
     * 게시물 검색
     *
     * @param query 검색 쿼리 (keyword, page, size 모두 포함)
     * @return 검색 결과 페이지
     */
    PageResult<PostSearchResult> searchPosts(PostSearchQuery query);
}

