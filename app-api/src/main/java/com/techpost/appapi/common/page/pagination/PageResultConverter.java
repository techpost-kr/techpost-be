package com.techpost.appapi.common.page.pagination;

import com.techpost.appapi.common.page.application.PageResult;
import org.springframework.data.domain.Page;

/**
 * Spring Page 변환 유틸리티
 */
public final class PageResultConverter {

    private PageResultConverter() {
        // 유틸리티 클래스는 인스턴스화 방지
    }

    /**
     * Spring Page를 PageResult로 변환
     *
     * @param page Spring Data Page
     * @return 프레임워크 독립적인 PageResult
     */
    public static <T> PageResult<T> fromSpringPage(Page<T> page) {
        return PageResult.of(
                page.getContent(),
                page.getTotalElements(),
                page.getNumber(),
                page.getSize()
        );
    }

}

