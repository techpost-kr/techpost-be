package com.techpost.appapi.post.adapter.out.persistence.postsearch;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techpost.appapi.common.dto.page.PageResult;
import com.techpost.appapi.common.pagination.PaginationHelper;

import com.techpost.appapi.post.application.port.in.postsearch.PostSearchQuery;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchResult;
import com.techpost.appapi.post.application.port.out.postsearch.PostSearchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import static com.techpost.appapi.common.util.QuerydslUtils.contains;
import static com.techpost.appapi.common.util.QuerydslUtils.eq;
import static com.techpost.appapi.post.adapter.out.persistence.entity.QPostJpaEntity.postJpaEntity;

/**
 * 게시물 조회 영속성 어댑터 (아웃바운드 어댑터)
 *
 * 헥사고날 아키텍처:
 * - LoadPostPort 구현 (Application Layer의 Port)
 * - QueryDSL 사용 (인프라 구현 세부사항 - 교체 가능)
 * - Application Layer는 이 클래스의 존재를 모름
 *
 * 네이밍 철학:
 * - "Persistence" = 영속성 계층 (기술 중립적) ✅
 * - "QueryDSL"을 이름에 넣지 않음 (구현 세부사항)
 * - 미래에 JPA Criteria, JOOQ 등으로 변경해도 이름 유지 가능
 */
@Component
@RequiredArgsConstructor
public class PostSearchPersistenceAdapter implements PostSearchPort {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public PageResult<PostSearchResult> searchPosts(PostSearchQuery query) {
        // PostSearchQuery에서 페이징 정보 추출 -> Spring Pageable 변환 (Adapter 경계에서)
        org.springframework.data.domain.Pageable pageable = PageRequest.of(query.page(), query.size());

        JPAQuery<PostSearchResult> contentQuery = buildSearchQuery(query, Projections.constructor(PostSearchResult.class, postJpaEntity));
        JPAQuery<Long> countQuery = buildSearchQuery(query, postJpaEntity.count());

        Page<PostSearchResult> springPage = PaginationHelper.paginate(contentQuery, countQuery, pageable);

        // Spring Page -> Application PageResult 변환 (Adapter 경계에서)
        return PageResult.fromSpringPage(springPage);
    }

    /**
     * QueryDSL 검색 쿼리 빌드
     */
    private <T> JPAQuery<T> buildSearchQuery(PostSearchQuery query, Expression<T> expression) {
        return jpaQueryFactory.select(expression)
                .from(postJpaEntity)
                .where(ExpressionUtils.or(
                        eq(postJpaEntity.publisher, query.getPublisher()),
                        contains(postJpaEntity.title, query.keyword())
                ))
                .orderBy(postJpaEntity.publishedAt.desc());
    }
}

