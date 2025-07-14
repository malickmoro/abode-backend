/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.controller;

import com.morosa.abode.dto.NurseApplicationAdminDTO;
import com.morosa.abode.payload.request.ApprovalRequest;
import com.morosa.abode.payload.request.RejectionRequest;
import com.morosa.abode.payload.response.ApiReponseWithList;
import com.morosa.abode.service.NurseApplicationService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author plutus
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final NurseApplicationService applicationService;

    @PostMapping("/nurse-applications/approve")
    public ResponseEntity<?> approve(@RequestBody @Valid ApprovalRequest request) {
        applicationService.approve(request);
        return ResponseEntity.ok("Application approved");

    }

    @PostMapping("/nurse-applications/reject")
    public ResponseEntity<?> reject(@RequestBody @Valid RejectionRequest request) {
        applicationService.reject(request);
        return ResponseEntity.ok("Application rejected");
    }
    
    @GetMapping("/nurse-applications/pending")
    public ResponseEntity<?> pending(){
        List<NurseApplicationAdminDTO> pendingList = applicationService.getPendingApplications();
        return ResponseEntity.ok(new ApiReponseWithList("00", true, pendingList));
    }
    
    @GetMapping("/nurse-applications/approved")
    public ResponseEntity<?> approved(){
        List<NurseApplicationAdminDTO> approvedList = applicationService.getApprovedApplications();
        return ResponseEntity.ok(new ApiReponseWithList("00", true, approvedList));
    }
}
