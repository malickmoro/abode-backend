/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.dto;

import com.morosa.abode.entity.Location;
import com.morosa.abode.entity.NurseApplication;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 *
 * @author plutus
 */
@Data
public class NurseApplicationAdminDTO {

    private String applicationId;
    private String firstName;
    private String surname;
    private String email;
    private String phone;
    private String photoUrl;
    private String idCardUrl;
    private String nssCertificateUrl;
    private String status;
    private LocationDTO location;
    private List<String> services;
    private String reason; // in case of rejection
    private LocalDateTime createdAt;

    public static NurseApplicationAdminDTO from(NurseApplication application) {
        NurseApplicationAdminDTO dto = new NurseApplicationAdminDTO();
        dto.setApplicationId(application.getApplicationId());
        dto.setFirstName(application.getFirstName());
        dto.setSurname(application.getSurname());
        dto.setEmail(application.getEmail());
        dto.setPhone(application.getPhone());
        dto.setPhotoUrl(application.getPhotoUrl());
        dto.setIdCardUrl(application.getIdCardUrl());
        dto.setNssCertificateUrl(application.getNssCertificateUrl());
        dto.setStatus(application.getStatus().name());
        dto.setServices(application.getServices());
        dto.setReason(application.getRejectionReason());
        dto.setCreatedAt(application.getCreatedAt());
        
        Location loc = application.getLocation();
        LocationDTO locDto = new LocationDTO();
        locDto.setGpsAddress(loc.getGpsAddress());
        locDto.setRegion(loc.getRegion());
        locDto.setPostCode(loc.getPostCode());
        locDto.setLongitude(loc.getLongitude());
        locDto.setLatitude(loc.getLatitude());
        locDto.setDistrict(loc.getDistrict());
        dto.setLocation(locDto);

        return dto;
    }

}
