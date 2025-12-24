package com.techpost.common.crypto.symmetric;

import com.techpost.common.crypto.symmetric.enums.AESKeySize;

public class AES256Cipher extends AESCipher {

    public AES256Cipher(byte[] keyBytes) {
        super(AESKeySize._256, keyBytes);
    }
}
