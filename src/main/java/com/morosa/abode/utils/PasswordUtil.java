/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.utils;

import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 *
 * @author plutus
 */
@Component
public class PasswordUtil {

    public String generateTemporaryPassword(){
        return UUID.randomUUID().toString().replaceAll("_", "").substring(0, 8);
    }
    
    public String hashPassword(String password) {
        return "";
    }

    public boolean verifyPassword(String hash, String password) {
        return true;
    }
}
