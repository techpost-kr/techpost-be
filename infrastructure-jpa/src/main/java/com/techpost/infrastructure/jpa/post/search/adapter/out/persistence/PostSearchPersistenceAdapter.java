package com.techpost.infrastructure.jpa.post.search.adapter.out.persistence;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techpost.application.common.page.PageResult;
import com.techpost.application.post.search.port.in.PostSearchQuery;
import com.techpost.application.post.search.port.in.PostSearchResult;
import com.techpost.application.post.search.port.out.PostSearchPort;
import com.techpost.domain.post.model.Publisher;
import com.techpost.infrastructure.jpa.common.util.PaginationHelper;
import com.techpost.infrastructure.jpa.post.common.mapper.PostEntityMapper;
import com.techpost.infrastructure.jpa.post.common.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.techpost.infrastructure.jpa.common.util.QuerydslUtils.contains;
import static com.techpost.infrastructure.jpa.common.util.QuerydslUtils.eq;
import static com.techpost.infrastructure.jpa.post.entity.QPostJpaEntity.postJpaEntity;

/**
 * Post 영속성 어댑터
 * - LoadPostPort, SavePostPort 구현
 * - 도메인 포트(out)를 JPA로 구현
 */
@Component
@RequiredArgsConstructor
public class PostSearchPersistenceAdapter implements PostSearchPort {

    private final PostJpaRepository postJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final PostEntityMapper mapper;

    @Override
    public PageResult<PostSearchResult> search(PostSearchQuery query) {
        JPAQuery<PostSearchResult> contentQuery = buildSearchQuery(
                query,
                Projections.constructor(
                        PostSearchResult.class,
                        postJpaEntity.postId,
                        postJpaEntity.publisher,
                        postJpaEntity.title,
                        postJpaEntity.url,
                        postJpaEntity.publishedAt
                )
        ).orderBy(postJpaEntity.publishedAt.desc());

        JPAQuery<Long> countQuery = buildCountQuery(query);

        Pageable pageable = PageRequest.of(query.pageQuery().page(), query.pageQuery().size());
        return PaginationHelper.paginate(contentQuery, countQuery, pageable);
    }

    @Override
    public List<PostSearchResult> searchByPublisher(Publisher publisher) {
        return postJpaRepository.findByPublisher(publisher).stream()
                .map(mapper::toSearchResult)
                .collect(Collectors.toList());
    }

    /**
     * QueryDSL 검색 쿼리 빌드 (ORDER BY 제외)
     */
    private <T> JPAQuery<T> buildSearchQuery(PostSearchQuery query, Expression<T> expression) {
        return jpaQueryFactory
                .select(expression)
                .from(postJpaEntity)
                .where(ExpressionUtils.or(
                        eq(postJpaEntity.publisher, query.getPublisher()),
                        contains(postJpaEntity.title, query.keyword())
                ));
    }

    /**
     * COUNT 쿼리 빌드 (ORDER BY 없음)
     */
    private JPAQuery<Long> buildCountQuery(PostSearchQuery query) {
        return jpaQueryFactory
                .select(postJpaEntity.count())
                .from(postJpaEntity)
                .where(ExpressionUtils.or(
                        eq(postJpaEntity.publisher, query.getPublisher()),
                        contains(postJpaEntity.title, query.keyword())
                ));
    }
}
