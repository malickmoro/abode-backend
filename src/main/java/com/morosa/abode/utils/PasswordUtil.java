/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.utils;

import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author plutus
 */
@Component
public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String generateTemporaryPassword() {
        return UUID.randomUUID().toString().replaceAll("_", "").substring(0, 8);
    }

    public static String hash(String password) {
        return encoder.encode(password);
    }

    public static boolean match(String raw, String hashed) {
        return encoder.matches(raw, hashed);
    }
}
