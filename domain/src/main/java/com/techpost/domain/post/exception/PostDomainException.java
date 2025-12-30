package com.techpost.domain.post.exception;

/**
 * Post 도메인 예외
 */
public class PostDomainException extends RuntimeException {

    public PostDomainException(String message) {
        super(message);
    }

    public PostDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

