package com.techpost.application.common.page;

import java.util.List;

/**
 * 페이지 결과
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
}

