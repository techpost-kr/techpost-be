package com.techpost.appapi.common.dto.page;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponse<T> {
    private final List<T> content;  // 실제 데이터 목록
    private final PageData pageData;

    // 순수 데이터 기반 생성자 (권장)
    public PageResponse(List<T> content, long totalElements, int totalPages, int currentPage) {
        this.content = content;
        this.pageData = PageData.of(content.size(), totalElements, totalPages, currentPage);
    }

    /**
     * @param pageNumber    현재 페이지 번호 (1부터 시작)
     * @param pageSize      페이지 크기
     * @param totalElements 전체 레코드 수
     * @param totalPages    전체 페이지 수
     * @param isLast        마지막 페이지 여부
     */
    private record PageData(
            int pageNumber,
            int pageSize,
            long totalElements,
            int totalPages,
            boolean isLast) {

            public static PageData of(int pageSize, long totalElements, int totalPages, int currentPage) {
                return new PageData(
                        currentPage + 1, // 0-based -> 1-based
                        pageSize,
                        totalElements,
                        totalPages,
                        currentPage >= totalPages - 1
                );
            }
        }

    /**
     * 순수 데이터로 PageResponse 생성 (권장)
     */
    public static <T> PageResponse<T> of(List<T> content, long totalElements, int totalPages, int currentPage) {
        return new PageResponse<>(content, totalElements, totalPages, currentPage);
    }
}
