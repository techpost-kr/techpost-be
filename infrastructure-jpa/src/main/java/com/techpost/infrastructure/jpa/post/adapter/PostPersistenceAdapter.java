package com.techpost.infrastructure.jpa.post.adapter;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.techpost.application.common.page.PageResult;
import com.techpost.application.post.port.in.PostSearchQuery;
import com.techpost.application.post.port.in.PostSearchResult;
import com.techpost.application.post.port.out.PostSearchPort;
import com.techpost.application.post.port.out.PostSavePort;
import com.techpost.domain.post.model.Post;
import com.techpost.domain.post.model.Publisher;
import com.techpost.infrastructure.jpa.common.util.PaginationHelper;
import com.techpost.infrastructure.jpa.post.entity.PostJpaEntity;
import com.techpost.infrastructure.jpa.post.mapper.PostEntityMapper;
import com.techpost.infrastructure.jpa.post.repository.PostJpaRepository;
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
public class PostPersistenceAdapter implements PostSearchPort, PostSavePort {

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
        );

        JPAQuery<Long> countQuery = buildSearchQuery(query, postJpaEntity.count());

        Pageable pageable = PageRequest.of(query.pageQuery().page(), query.pageQuery().size());
        return PaginationHelper.paginate(contentQuery, countQuery, pageable);
    }

    @Override
    public List<Post> searchByPublisher(Publisher publisher) {
        return postJpaRepository.findByPublisher(publisher).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Post save(Post post) {
        PostJpaEntity entity = mapper.toEntity(post);
        PostJpaEntity saved = postJpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Post> saveAll(List<Post> posts) {
        List<PostJpaEntity> entities = posts.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        List<PostJpaEntity> saved = postJpaRepository.saveAll(entities);

        return saved.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
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
                        contains(postJpaEntity.title, query.keyword())
                ))
                .orderBy(postJpaEntity.publishedAt.desc());
    }
}
