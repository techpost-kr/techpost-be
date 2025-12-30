package com.techpost.appapi.common.page.pagination.strategy;

import com.querydsl.jpa.impl.JPAQuery;
import com.techpost.appapi.common.page.application.PageResult;
import org.springframework.data.domain.Pageable;

public interface PaginationStrategy<T> {

    PageResult<T> paginate(JPAQuery<T> contentQuery, JPAQuery<Long> countQuery, Pageable pageable);

}
