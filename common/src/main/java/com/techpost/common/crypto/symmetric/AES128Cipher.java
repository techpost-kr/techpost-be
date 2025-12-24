package com.techpost.common.crypto.symmetric;

import com.techpost.common.crypto.symmetric.enums.AESKeySize;

public class AES128Cipher extends AESCipher {

    public AES128Cipher(byte[] keyBytes) {
        super(AESKeySize._128, keyBytes);
    }
}
