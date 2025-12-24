package com.techpost.domain.post.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techpost.domain.common.pagination.PaginationHelper;
import com.techpost.domain.post.dto.criteria.PostCriteria;
import com.techpost.domain.post.dto.result.PostResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.techpost.domain.common.util.QuerydslUtils.contains;
import static com.techpost.domain.common.util.QuerydslUtils.eq;
import static com.techpost.domain.post.entity.QPost.post;

@RequiredArgsConstructor
@Repository
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<PostResult> searchPosts(PostCriteria criteria, Pageable pageable) {
        JPAQuery<PostResult> contentQuery = this.postQuery(criteria, Projections.constructor(PostResult.class, post));
        JPAQuery<Long> countQuery = this.postQuery(criteria, post.count());
        return PaginationHelper.paginate(contentQuery, countQuery, pageable);
    }

    private <T> JPAQuery<T> postQuery(PostCriteria criteria, Expression<T> expression) {
        return jpaQueryFactory.select(expression)
                .from(post)
                .where(ExpressionUtils.or(eq(post.publisher, criteria.getPublisher()), contains(post.title, criteria.getQuery())))
                .orderBy(post.publishedDateTime.desc());
    }


}
