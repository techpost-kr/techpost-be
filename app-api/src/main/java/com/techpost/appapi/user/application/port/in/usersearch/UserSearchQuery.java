package com.techpost.appapi.user.application.port.in.usersearch;

import com.techpost.appapi.common.page.application.PageQuery;

/**
 * 사용자 검색 쿼리 (UserSearchUseCase 입력)
 */
public record UserSearchQuery(
        String name,
        PageQuery pageQuery
) {

    public UserSearchQuery {
        if (name == null) {
            name = "";
        }
    }

    public static UserSearchQuery of(String name, int page, int size) {
        return new UserSearchQuery(name, PageQuery.of(page, size));
    }

}

