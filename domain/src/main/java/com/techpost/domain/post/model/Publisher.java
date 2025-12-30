package com.techpost.domain.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 발행처 enum
 */
@Getter
@AllArgsConstructor
public enum Publisher {

    KAKAO("카카오"),
    NAVER("네이버"),
    ;

    private final String name;

    public static Optional<Publisher> findByName(String name) {
        if (name == null || name.trim().isEmpty()) return Optional.empty();
        return Arrays.stream(values())
                .filter(item -> item.getName().contains(name))
                .findFirst();
    }
}

