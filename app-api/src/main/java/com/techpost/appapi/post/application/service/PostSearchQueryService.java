package com.techpost.appapi.post.application.service;

import com.techpost.appapi.common.dto.page.PageResult;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchQuery;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchResult;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchUseCase;
import com.techpost.appapi.post.application.port.out.postsearch.PostSearchPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 게시물 조회 애플리케이션 서비스 (유스케이스 구현)
 *
 * 헥사고날 아키텍처:
 * - SearchPostUseCase 구현 (인바운드 포트 구현)
 * - LoadPostPort 사용 (아웃바운드 포트 사용)
 * - Controller는 이 클래스를 직접 알지 못함 (인터페이스를 통해서만 접근)
 *
 * DTO 변환 최소화:
 * - Request → Query (Web → Application 경계만)
 * - PostCriteria 제거 (Query가 직접 사용됨)
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostSearchQueryService implements PostSearchUseCase {

    private final PostSearchPort postSearchPort;

    @Override
    public PageResult<PostSearchResult> search(PostSearchQuery query) {
        log.info("게시물 검색 시작: keyword={}, page={}, size={}",
                query.keyword(), query.page(), query.size());

        // 아웃바운드 포트를 통한 검색 실행 (Query에 모든 정보 포함)
        PageResult<PostSearchResult> result = postSearchPort.searchPosts(query);

        log.info("게시물 검색 완료: 총 {} 건", result.totalElements());
        return result;
    }
}
