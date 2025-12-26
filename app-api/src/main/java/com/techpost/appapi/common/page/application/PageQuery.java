package com.techpost.appapi.common.page.application;

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
     *
     * 검증 정책:
     * - page: 음수면 0으로 보정
     * - size: MIN_SIZE 미만이면 DEFAULT_SIZE 적용
     * - size: MAX_SIZE 초과하면 MAX_SIZE로 제한
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
            size = MAX_SIZE;  // ⭐ 최대값 제한으로 서버 보호
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

    /**
     * 첫 페이지 (크기 지정)
     */
    public static PageQuery firstPage(int size) {
        return new PageQuery(0, size);
    }

    /**
     * 페이지 크기 상수 접근
     */
    public static int getDefaultSize() {
        return DEFAULT_SIZE;
    }

    public static int getMaxSize() {
        return MAX_SIZE;
    }

    /**
     * Spring Pageable로 변환 (Adapter Layer에서만 사용)
     */
    public org.springframework.data.domain.Pageable toSpringPageable() {
        return org.springframework.data.domain.PageRequest.of(page, size);
    }

    /**
     * 다음 페이지
     */
    public PageQuery nextPage() {
        return new PageQuery(page + 1, size);
    }

    /**
     * 이전 페이지
     */
    public PageQuery previousPage() {
        return new PageQuery(Math.max(0, page - 1), size);
    }
}

