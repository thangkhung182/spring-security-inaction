package com.trungnguyen.springsecurityinaction.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class GenerateCodeUtil {
    public static String generateCode() {
        try {
            var random = SecureRandom.getInstanceStrong();
            int code = random.nextInt(900000) + 100000; // 6 digit
            return String.valueOf(code);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating random code.");
        }
    }
}
