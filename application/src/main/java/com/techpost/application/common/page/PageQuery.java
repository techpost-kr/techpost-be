package com.techpost.application.common.page;

/**
 * 페이지 요청 (Application Layer)
 */
public record PageQuery(
        int page,
        int size
) {
    // 페이지 크기 상수 (업계 표준에 따른 설정)
    private static final int DEFAULT_SIZE = 20;   // 기본 페이지 크기
    private static final int MIN_SIZE = 1;        // 최소 1개
    private static final int MAX_SIZE = 100;      // 최대 100개 (서버 부하 고려)

    /**
     * Compact Constructor (검증 및 제한)
     */
    public PageQuery {
        // 페이지 번호 검증
        if (page < 0) {
            page = 0;
        }

        // 페이지 크기 검증 및 제한
        if (size < MIN_SIZE) {
            size = DEFAULT_SIZE;
        }
        if (size > MAX_SIZE) {
            size = MAX_SIZE;  // 최대값 제한으로 서버 보호
        }
    }

    /**
     * 정적 팩토리 메서드
     */
    public static PageQuery of(int page, int size) {
        return new PageQuery(page, size);
    }

    /**
     * 기본 페이징 (첫 페이지, 기본 크기)
     */
    public static PageQuery defaultPage() {
        return new PageQuery(0, DEFAULT_SIZE);
    }
}
