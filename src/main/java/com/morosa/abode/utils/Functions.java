/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.utils;

import java.util.UUID;

/**
 *
 * @author plutus
 */
public class Functions {

    public static String generateApplicationId() {
        return "APP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
