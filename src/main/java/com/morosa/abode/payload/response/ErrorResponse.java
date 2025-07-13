/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author plutus
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private HttpStatus status;
    private String msg;
}
