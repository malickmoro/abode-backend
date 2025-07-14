/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author plutus
 */
@Data
@AllArgsConstructor
public class ApiResponse {
    private String code;
    private boolean success;
    private Object data;
}
