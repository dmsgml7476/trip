package com.trip.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHashUtil {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        String rawPassword = "a123456"; // 여기에 암호화하고 싶은 비밀번호 입력
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("암호화된 비밀번호: " + encodedPassword);
    }
}
