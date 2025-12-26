package com.techpost.appapi.common.page.pagination;

import com.querydsl.jpa.impl.JPAQuery;
import com.techpost.appapi.common.page.application.PageResult;
import org.springframework.data.domain.Pageable;

public final class PaginationHelper {

    public static <T> PageResult<T> paginate(JPAQuery<T> contentQuery,
                                             JPAQuery<Long> countQuery,
                                             Pageable pageable) {
        return paginate(contentQuery, countQuery, pageable, new DefaultPaginationStrategy<>());
    }
    public static <T> PageResult<T> paginate(JPAQuery<T> contentQuery,
                                             JPAQuery<Long> countQuery,
                                             Pageable pageable,
                                             PaginationStrategy<T> strategy) {

        return strategy.paginate(contentQuery, countQuery, pageable);
    }


}
