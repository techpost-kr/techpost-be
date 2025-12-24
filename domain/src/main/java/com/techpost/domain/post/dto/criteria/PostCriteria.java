package com.techpost.domain.post.dto.criteria;

import com.techpost.domain.post.enums.Publisher;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCriteria {

    private final String query;

    public static PostCriteria of(String query) {
        return new PostCriteria(query);
    }

    public Publisher getTechBlogEnum() {
        return Publisher.findByName(this.query).orElse(null);
    }
}
