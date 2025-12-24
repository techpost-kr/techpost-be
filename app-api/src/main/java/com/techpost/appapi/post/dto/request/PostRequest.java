package com.techpost.appapi.post.dto.request;

import com.techpost.appapi.common.dto.request.PageRequest;
import com.techpost.domain.post.dto.criteria.PostCriteria;
import lombok.Getter;

@Getter
public class PostRequest extends PageRequest {

    private final String query;

    protected PostRequest(Integer pageNumber,
                                  Integer pageSize,
                                  String query) {
        super(pageNumber, pageSize);
        this.query = query;
    }

    public PostCriteria toPostCriteria() {
        return PostCriteria.of(this.query);
    }
}
