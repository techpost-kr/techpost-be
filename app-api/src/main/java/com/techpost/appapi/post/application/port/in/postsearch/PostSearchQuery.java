package com.techpost.appapi.post.application.port.in.postsearch;

import com.techpost.appapi.post.domain.enums.Publisher;

/**
 * 게시물 검색 쿼리 (PostSearchUseCase 입력)
 *
 * UseCase와 함께 위치:
 * - PostSearchUseCase의 입력 계약
 * - 유스케이스별로 그룹핑
 */
public record PostSearchQuery(
        String keyword,
        int page,
        int size
) {
    public static PostSearchQuery of(String keyword, int page, int size) {
        return new PostSearchQuery(keyword, page, size);
    }

    /**
     * Compact Constructor (검증)
     */
    public PostSearchQuery {
        if (keyword == null) {
            keyword = "";
        }
        if (page < 0) {
            page = 0;
        }
        if (size < 1) {
            size = 10;
        }
        if (size > 100) {
            size = 100;
        }
    }

    /**
     * Publisher 변환 (비즈니스 로직)
     */
    public Publisher getPublisher() {
        return Publisher.findByName(this.keyword).orElse(null);
    }
}

