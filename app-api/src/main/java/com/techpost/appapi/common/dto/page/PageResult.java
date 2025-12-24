package com.techpost.appapi.common.dto.page;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 페이지네이션 결과 (Application Layer)
 * - Spring Data Page에 의존하지 않는 순수 애플리케이션 모델
 * - Record: 불변 객체, 자동 생성자/getter 제공
 */
public record PageResult<T>(
        List<T> content,
        long totalElements,
        int totalPages,
        int currentPage,
        int pageSize,
        boolean hasNext,
        boolean hasPrevious
) {
    /**
     * 정적 팩토리 메서드 (계산 로직 포함)
     */
    public static <T> PageResult<T> of(
            List<T> content,
            long totalElements,
            int currentPage,
            int pageSize
    ) {
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);
        boolean hasNext = currentPage < totalPages - 1;
        boolean hasPrevious = currentPage > 0;

        return new PageResult<>(
                content,
                totalElements,
                totalPages,
                currentPage,
                pageSize,
                hasNext,
                hasPrevious
        );
    }

    /**
     * Spring Data Page에서 변환
     * (Adapter Layer에서만 사용)
     */
    public static <T> PageResult<T> fromSpringPage(Page<T> page) {
        return of(
                page.getContent(),
                page.getTotalElements(),
                page.getNumber(),
                page.getSize()
        );
    }
}

