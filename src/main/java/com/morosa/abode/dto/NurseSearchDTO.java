/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.dto;

import com.morosa.abode.entity.Nurse;
import java.util.List;
import lombok.Data;

/**
 *
 * @author plutus
 */
@Data
public class NurseSearchDTO {

    private String id;
    private String fullName;
    private double rating;
    private List<String> services;
    private String region;

     public static NurseSearchDTO from(Nurse nurse) {
        NurseSearchDTO dto = new NurseSearchDTO();
        dto.setId(nurse.getId());
        dto.setFullName(nurse.getFirstName() + " " + nurse.getSurname());
        dto.setRating(nurse.getAverageRating());

        List<String> services = nurse.getServices()
                .stream()
                .map(String::valueOf)
                .toList();

        dto.setServices(services);

        if (nurse.getLocation() != null) {
            dto.setRegion(nurse.getLocation().getRegion());
        }

        return dto;
    }

}
