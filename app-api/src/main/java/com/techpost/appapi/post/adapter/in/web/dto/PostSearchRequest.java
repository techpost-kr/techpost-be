package com.techpost.appapi.post.adapter.in.web.dto;

import com.techpost.appapi.common.dto.page.PageRequest;
import com.techpost.appapi.post.application.port.in.postsearch.PostSearchQuery;
import lombok.Getter;

@Getter
public class PostSearchRequest extends PageRequest {

    private final String query;

    protected PostSearchRequest(
            Integer pageNumber,
            Integer pageSize,
            String query) {
        super(pageNumber, pageSize);

        this.query = query;
    }

    /**
     * Request -> Query 변환 (헥사고날 아키텍처)
     */
    public PostSearchQuery toPostSearchQuery() {
        return PostSearchQuery.of(
                this.query,
                this.getPageNumber(),
                this.getPageSize()
        );
    }
}
