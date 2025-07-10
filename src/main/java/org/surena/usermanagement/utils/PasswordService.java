package org.surena.usermanagement.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hashPassword(String plainPass) {
        return passwordEncoder.encode(plainPass);
    }

    public static boolean verifyPassword(String plainPass, String hashedPass) {
        return passwordEncoder.matches(plainPass, hashedPass);
    }
}
