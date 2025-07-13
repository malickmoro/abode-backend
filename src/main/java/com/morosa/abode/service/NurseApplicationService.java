/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.service;

import com.morosa.abode.entity.NurseApplication;
import com.morosa.abode.entity.enums.ApplicationStatus;
import com.morosa.abode.exceptions.ImageAssetNotFoundException;
import com.morosa.abode.payload.request.NurseApplicationRequest;
import com.morosa.abode.repository.NurseApplicationRepository;
import com.morosa.abode.utils.CloudinaryUtil;
import com.morosa.abode.utils.Functions;
import com.morosa.abode.utils.GPSUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author plutus
 */
@Service
@RequiredArgsConstructor
public class NurseApplicationService {

    @Autowired
    private final NurseApplicationRepository applicationRepository;

    @Autowired
    private CloudinaryUtil cloudinaryUtil;
    
    @Autowired
    private GPSUtil gpsUtil;

    private String idCardUrl, certUrl, photoUrl;

    public void apply(NurseApplicationRequest req) {
        uploadFilesToCloudinary(req);
        saveApplication(req);
        idCardUrl = null;
        certUrl = null;
        photoUrl = null;
    }

    private void uploadFilesToCloudinary(NurseApplicationRequest req) {
        if (req.getIdCard() == null) {
            throw new ImageAssetNotFoundException("Id Card is a requirement for application");
        }

        if (req.getNssCertificate() == null) {
            throw new ImageAssetNotFoundException("NSS Certificate is requires for application");
        }
        
        if (req.getPhoto()== null) {
            throw new ImageAssetNotFoundException("Photo is requires for application");
        }

        idCardUrl = cloudinaryUtil.uploadFile(req.getIdCard(), "ids", false, true);
        certUrl = cloudinaryUtil.uploadFile(req.getNssCertificate(), "certs", false, true);
        photoUrl = cloudinaryUtil.uploadFile(req.getPhoto(), "photo", false, true);
    }

    private void saveApplication(NurseApplicationRequest req) {
        NurseApplication application = new NurseApplication();
        application.setApplicationId(Functions.generateApplicationId());
        application.setEmail(req.getEmail());
        application.setPhone(req.getPhone());
        application.setFirstName(req.getFirstName());
        application.setSurname(req.getSurname());
        application.setIdCardUrl(idCardUrl);
        application.setNssCertificateUrl(certUrl);
        application.setStatus(ApplicationStatus.PENDING);
        application.setPhotoUrl(photoUrl);
        application.setLocation(gpsUtil.getLocation(req.getGpsAddress()));
        application.setServices(req.getServices());
        applicationRepository.save(application);
    }
}
