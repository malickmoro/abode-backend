/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author plutus
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "LOCATION")
public class Location extends EntityModel {

    private String gpsAddress;
    private String region;
    private String postCode;
    private Double longitude;
    private Double latitude;
    private String district;
}
