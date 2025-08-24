package com.tp.common.response.exception;

import com.tp.common.response.ApiResponse;
import com.tp.common.response.enums.ApiResponseEnumInterface;
import lombok.Getter;

@Getter
public class ApiResponseException extends RuntimeException {
    private final ApiResponse<Object> apiResponse;

    public ApiResponseException(ApiResponseEnumInterface apiResponseEnum) {
        super(apiResponseEnum.getMessage());
        this.apiResponse = ApiResponse.fail(apiResponseEnum);
    }

    public <T> ApiResponseException(ApiResponseEnumInterface apiResponseEnum, T data) {
        super(apiResponseEnum.getMessage());
        this.apiResponse = ApiResponse.fail(apiResponseEnum, data);
    }

}
