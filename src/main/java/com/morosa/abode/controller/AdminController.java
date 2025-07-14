/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.controller;

import com.morosa.abode.payload.request.ApprovalRequest;
import com.morosa.abode.payload.request.RejectionRequest;
import com.morosa.abode.service.NurseApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
