package com.techpost.appapi.post.application.port.in.postsearch;

import com.techpost.appapi.common.dto.page.PageResult;

/**
 * 게시물 검색 유스케이스 (인바운드 포트)
 *
 * 헥사고날 아키텍처:
 * - Application Layer에서 정의
 * - Web Adapter(Controller)가 이 인터페이스를 통해 호출
 * - Service가 이 인터페이스를 구현
 *
 * 유스케이스 계약:
 * - 입력: PostSearchQuery (같은 패키지)
 * - 출력: PostSearchResult (같은 패키지)
 * - 유스케이스와 입출력 객체를 함께 관리 ✅
 *
 * 네이밍:
 * - UseCase 접미사 = 비즈니스 유스케이스 표현
 * - PostSearch = 도메인 + 동작
 */
public interface PostSearchUseCase {

    /**
     * 게시물 검색
     *
     * @param query 검색 쿼리 (keyword, page, size 포함)
     * @return 검색 결과 페이지
     */
    PageResult<PostSearchResult> search(PostSearchQuery query);
}

