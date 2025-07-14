/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.dto;

import lombok.Data;

/**
 *
 * @author plutus
 */
@Data
public class LocationDTO {
    private String gpsAddress;
    private String region;
    private String postCode;
    private Double longitude;
    private Double latitude;
    private String district;
}

