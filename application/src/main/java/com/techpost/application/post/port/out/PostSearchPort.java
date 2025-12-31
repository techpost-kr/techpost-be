package com.techpost.application.post.port.out;

import com.techpost.application.common.page.PageResult;
import com.techpost.application.post.port.in.PostSearchQuery;
import com.techpost.application.post.port.in.PostSearchResult;
import com.techpost.domain.post.model.Post;
import com.techpost.domain.post.model.Publisher;

import java.util.List;

/**
 * 게시물 조회 포트 (아웃바운드)
 */
public interface PostSearchPort {

    /**
     * 게시물 검색
     *
     * @param query 검색 쿼리
     * @return 검색 결과 페이지
     */
    PageResult<PostSearchResult> search(PostSearchQuery query);

    /**
     * 발행처별 게시물 조회
     *
     * @param publisher 발행처
     * @return 게시물 목록
     */
    List<PostSearchResult> searchByPublisher(Publisher publisher);
}
