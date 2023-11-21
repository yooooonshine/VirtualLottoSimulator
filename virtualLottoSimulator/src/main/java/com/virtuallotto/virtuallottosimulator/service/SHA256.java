package com.virtuallotto.virtuallottosimulator.service;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHA256 {
    private final String CANT_USE_ENCRYPT_ALGORITHM = "[ERROR] 암호화 알고리즘을 사용할 수 없습니다. ";

    public String encrypt(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());

            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.getMessage();
            throw new IllegalArgumentException(CANT_USE_ENCRYPT_ALGORITHM);
        }

    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
