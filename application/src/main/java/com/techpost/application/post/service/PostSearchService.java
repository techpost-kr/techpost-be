package com.techpost.application.post.service;

import com.techpost.application.common.page.PageResult;
import com.techpost.application.post.port.in.PostSearchQuery;
import com.techpost.application.post.port.in.PostSearchResult;
import com.techpost.application.post.port.in.PostSearchUseCase;
import com.techpost.application.post.port.out.PostSearchPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시물 조회 애플리케이션 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class PostSearchService implements PostSearchUseCase {

    private final PostSearchPort postSearchPort;

    @Override
    public PageResult<PostSearchResult> search(PostSearchQuery query) {
        return postSearchPort.search(query);
    }
}

