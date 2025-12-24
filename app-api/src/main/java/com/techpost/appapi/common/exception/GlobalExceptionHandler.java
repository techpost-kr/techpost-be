package com.techpost.appapi.common.exception;

import com.techpost.common.response.ApiResponse;
import com.techpost.common.response.enums.ApiResponseEnum;
import com.techpost.common.response.exception.ApiResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiResponseException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Object> handleApiResponseException(ApiResponseException ex) {
        log.error("handleApiResponseException: {}", ex.getMessage(), ex);
        return ex.getApiResponse();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> handleException(Exception ex) {
        log.error("handleException: {}", ex.getMessage(), ex);
        return ApiResponse.fail(ApiResponseEnum.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValidException: {}", ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            errors.put(fieldName, defaultMessage);
        });

        return ApiResponse.fail(ApiResponseEnum.INTERNAL_SERVER_ERROR, errors);
    }
}
