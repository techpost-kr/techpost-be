package com.techpost.appapi.post.application.service;

import com.techpost.appapi.common.page.application.PageResult;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchQuery;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchResult;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchUseCase;
import com.techpost.appapi.post.application.port.out.postsearch.PostSearchPort;
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
public class PostSearchQueryService implements PostSearchUseCase {

    private final PostSearchPort postSearchPort;

    @Override
    public PageResult<PostSearchResult> search(PostSearchQuery query) {
        return postSearchPort.search(query);
    }
}
