package com.test.tuma.config;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; // 256 bits
        secureRandom.nextBytes(key);

        // Convert byte array to Base64 string
        String base64Key = Base64.getEncoder().encodeToString(key);

        // Convert byte array to Hex string
        StringBuilder hexString = new StringBuilder(64);
        for (byte b : key) {
            hexString.append(String.format("%02x", b));
        }

        System.out.println("Generated Secret Key (Base64): " + base64Key);
        System.out.println("Generated Secret Key (Hex): " + hexString.toString());
    }
}


