package com.techpost.application.post.port.in;

import com.techpost.application.common.page.PageQuery;
import com.techpost.domain.post.model.Publisher;

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
