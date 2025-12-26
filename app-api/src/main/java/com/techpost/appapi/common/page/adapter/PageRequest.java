package com.techpost.appapi.common.page.adapter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 웹 페이지 요청
 */
@Getter
@AllArgsConstructor
public class PageRequest {

    @NotNull(message = "페이지 번호는 필수입니다")
    @Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다")
    protected final Integer page;

    @NotNull(message = "페이지 크기는 필수입니다")
    @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다")
    protected final Integer size;

}

