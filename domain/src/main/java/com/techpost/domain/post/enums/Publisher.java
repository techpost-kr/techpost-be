package com.techpost.domain.post.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public enum Publisher {

    KAKAO("카카오"),
    NAVER("네이버"),
    ;

    private final String name;

    public static Optional<Publisher> findByName(String name) {
        if (!hasText(name)) return Optional.empty();
        return Arrays.stream(values())
                .filter(item -> item.getName().contains(name))
                .findFirst();
    }
}
