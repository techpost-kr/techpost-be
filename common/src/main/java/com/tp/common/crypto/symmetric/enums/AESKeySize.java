package com.tp.common.crypto.symmetric.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AESKeySize {
    _128("AES128", 128),
    _192("AES192", 192),
    _256("AES256", 256),
    ;

    private final String name;
    private final int bitSize;

    public int getByteSize() {
        return bitSize / 8;
    }

}
