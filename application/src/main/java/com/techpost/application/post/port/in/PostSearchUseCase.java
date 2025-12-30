package com.techpost.application.post.port.in;

import com.techpost.application.common.page.PageResult;

/**
 * 게시물 검색 유스케이스 (인바운드 포트)
 */
public interface PostSearchUseCase {

    /**
     * 게시물 검색
     *
     * @param query 검색 쿼리
     * @return 검색 결과 페이지
     */
    PageResult<PostSearchResult> search(PostSearchQuery query);
}

