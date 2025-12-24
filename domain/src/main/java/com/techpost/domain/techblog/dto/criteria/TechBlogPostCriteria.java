package com.techpost.domain.techblog.dto.criteria;

import com.techpost.domain.techblog.enums.TechBlogEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TechBlogPostCriteria {

    private final String query;

    public static TechBlogPostCriteria of(String query) {
        return new TechBlogPostCriteria(query);
    }

    public TechBlogEnum getTechBlogEnum() {
        return TechBlogEnum.findByName(this.query).orElse(null);
    }
}
