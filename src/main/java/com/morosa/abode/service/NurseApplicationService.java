/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.service;

import com.morosa.abode.entity.Nurse;
import com.morosa.abode.entity.NurseApplication;
import com.morosa.abode.entity.enums.ApplicationStatus;
import com.morosa.abode.exceptions.ApplicationNotFoundException;
import com.morosa.abode.exceptions.ImageAssetNotFoundException;
import com.morosa.abode.payload.request.ApprovalRequest;
import com.morosa.abode.payload.request.NurseApplicationRequest;
import com.morosa.abode.payload.request.RejectionRequest;
import com.morosa.abode.repository.NurseApplicationRepository;
import com.morosa.abode.repository.NurseRepository;
import com.morosa.abode.utils.CloudinaryUtil;
import com.morosa.abode.utils.Functions;
import com.morosa.abode.utils.GPSUtil;
import com.morosa.abode.utils.PasswordUtil;
import java.util.ArrayList;
import java.util.Optional;
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
    private final NurseRepository nurseRepository;

    @Autowired
    private CloudinaryUtil cloudinaryUtil;

    @Autowired
    private GPSUtil gpsUtil;

    @Autowired
    private PasswordUtil passwordUtil;

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

        if (req.getPhoto() == null) {
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

    public void approve(ApprovalRequest request) {
        Optional<NurseApplication> application = applicationRepository.findByApplicationId(request.getApplicationId());
        if (!application.isPresent()) {
            throw new ApplicationNotFoundException("Application with provided id not found");
        }

        NurseApplication found = application.get();
        found.setStatus(ApplicationStatus.APPROVED);
        createAndSaveNurseProfile(found);
        applicationRepository.save(found);
    }

    public void reject(RejectionRequest request) {
        Optional<NurseApplication> application = applicationRepository.findByApplicationId(request.getApplicationId());
        if (!application.isPresent()) {
            throw new ApplicationNotFoundException("Application with provided id not found");
        }

        NurseApplication found = application.get();
        found.setStatus(ApplicationStatus.REJECTED);
        found.setRejectionReason(request.getReason());
        applicationRepository.save(found);

    }

    private void createAndSaveNurseProfile(NurseApplication found) {
        Nurse profile = new Nurse();
        profile.setFirstName(found.getFirstName());
        profile.setSurname(found.getSurname());
        profile.setEmail(found.getEmail());
        profile.setLocation(found.getLocation());
        profile.setPassword(passwordUtil.generateTemporaryPassword());
        profile.setServices(new ArrayList<>(found.getServices()));
        profile.setPhone(found.getPhone());
        profile.setDob(found.getDob());
        profile.setResetPassword(true);
        nurseRepository.save(profile);
    }
}
