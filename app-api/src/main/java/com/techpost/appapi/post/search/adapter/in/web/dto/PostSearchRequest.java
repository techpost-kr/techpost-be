package com.techpost.appapi.post.search.adapter.in.web.dto;

import com.techpost.appapi.common.page.adapter.PageRequest;
import com.techpost.appapi.common.page.application.PageQuery;
import com.techpost.appapi.post.search.application.port.in.PostSearchQuery;
import lombok.Getter;

/**
 * 게시물 검색 요청
 */
@Getter
public class PostSearchRequest extends PageRequest {

    private final String keyword;

    public PostSearchRequest(
            Integer page,
            Integer size,
            String keyword) {
        super(page, size);
        this.keyword = keyword;
    }

    public PostSearchQuery toPostSearchQuery() {
        return PostSearchQuery.of(
                this.keyword,
                PageQuery.of(
                        super.page - 1,   // 1-based → 0-based
                        super.size
                )
        );
    }
}
