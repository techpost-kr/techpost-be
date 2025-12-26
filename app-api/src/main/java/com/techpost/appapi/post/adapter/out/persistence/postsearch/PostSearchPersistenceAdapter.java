package com.techpost.appapi.post.adapter.out.persistence.postsearch;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techpost.appapi.common.page.application.PageResult;
import com.techpost.appapi.common.page.pagination.PaginationHelper;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchQuery;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchResult;
import com.techpost.appapi.post.application.port.out.postsearch.PostSearchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.techpost.appapi.common.util.QuerydslUtils.contains;
import static com.techpost.appapi.common.util.QuerydslUtils.eq;
import static com.techpost.appapi.post.adapter.out.persistence.entity.QPostJpaEntity.postJpaEntity;

/**
 * 게시물 조회 영속성 어댑터
 */
@Repository
@RequiredArgsConstructor
public class PostSearchPersistenceAdapter implements PostSearchPort {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public PageResult<PostSearchResult> search(PostSearchQuery query) {

        JPAQuery<PostSearchResult> contentQuery =
                buildSearchQuery(
                        query,
                        Projections.constructor(
                                PostSearchResult.class,
                                postJpaEntity.postId,
                                postJpaEntity.publisher,
                                postJpaEntity.title,
                                postJpaEntity.url,
                                postJpaEntity.publishedAt)
                );

        JPAQuery<Long> countQuery =
                buildSearchQuery(
                        query,
                        postJpaEntity.count()
                );

        Pageable pageable = PageRequest.of(query.pageQuery().page(), query.pageQuery().size());
        return PaginationHelper.paginate(contentQuery, countQuery, pageable);
    }

    /**
     * QueryDSL 검색 쿼리 빌드
     */
    private <T> JPAQuery<T> buildSearchQuery(PostSearchQuery query, Expression<T> expression) {
        return jpaQueryFactory
                .select(expression)
                .from(postJpaEntity)
                .where(ExpressionUtils.or(
                        eq(postJpaEntity.publisher, query.getPublisher()),
                        contains(postJpaEntity.title, query.keyword()))
                )
                .orderBy(postJpaEntity.publishedAt.desc());
    }
}
