package com.techpost.appapi.post.search.application.port.in;

import com.techpost.appapi.common.page.application.PageQuery;
import com.techpost.appapi.post.domain.enums.Publisher;

/**
 * 게시물 검색 쿼리
 */
public record PostSearchQuery(
        String keyword,
        PageQuery pageQuery
) {
    public PostSearchQuery {
        if (keyword == null) {
            keyword = "";
        }
    }

    public static PostSearchQuery of(String keyword, PageQuery pageQuery) {
        return new PostSearchQuery(keyword, pageQuery);
    }

    public Publisher getPublisher() {
        return Publisher.findByName(this.keyword).orElse(null);
    }
}

