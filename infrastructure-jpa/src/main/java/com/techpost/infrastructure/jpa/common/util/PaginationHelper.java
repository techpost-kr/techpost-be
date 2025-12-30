package com.techpost.infrastructure.jpa.common.util;

import com.querydsl.jpa.impl.JPAQuery;
import com.techpost.application.common.page.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

/**
 * 페이지네이션 헬퍼
 */
public final class PaginationHelper {

    private PaginationHelper() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * QueryDSL 쿼리를 페이지네이션하여 PageResult로 반환
     */
    public static <T> PageResult<T> paginate(JPAQuery<T> contentQuery,
                                             JPAQuery<Long> countQuery,
                                             Pageable pageable) {
        // Content 조회
        List<T> content = contentQuery
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Spring Page 생성
        Page<T> springPage = PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);

        // PageResult로 변환
        return PageResult.of(
                springPage.getContent(),
                springPage.getTotalElements(),
                springPage.getNumber(),
                springPage.getSize()
        );
    }
}

