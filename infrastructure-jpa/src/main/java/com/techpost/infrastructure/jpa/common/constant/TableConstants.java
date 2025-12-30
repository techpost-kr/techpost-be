package com.techpost.infrastructure.jpa.common.constant;

/**
 * 테이블 상수
 */
public final class TableConstants {

    public static final String POST = "posts";
    public static final String USER = "users";

    private TableConstants() {
        throw new IllegalAccessError("Constant class");
    }
}

