package com.techpost.appapi.common.page.pagination;

import com.querydsl.jpa.impl.JPAQuery;
import com.techpost.appapi.common.page.application.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public class DefaultPaginationStrategy<T> implements PaginationStrategy<T> {

    @Override
    public PageResult<T> paginate(JPAQuery<T> contentQuery, JPAQuery<Long> countQuery, Pageable pageable) {

        List<T> content = contentQuery.offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Page<T> springPageResult = PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
        return PageResultConverter.fromSpringPage(springPageResult);

    }
}
