package com.techpost.appapi.post.search.application.port.out;

import com.techpost.appapi.common.page.application.PageResult;
import com.techpost.appapi.post.search.application.port.in.PostSearchQuery;
import com.techpost.appapi.post.search.application.port.in.PostSearchResult;

/**
 * 게시물 조회
 */
public interface PostSearchPort {

    /**
     * 게시물 검색
     *
     * @param query 검색 쿼리
     * @return 검색 결과 페이지
     */
    PageResult<PostSearchResult> search(PostSearchQuery query);
}

