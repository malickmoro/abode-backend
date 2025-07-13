/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.controller;

import com.morosa.abode.payload.request.NurseApplicationRequest;
import com.morosa.abode.service.NurseApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author plutus
 */
@RestController
@RequestMapping("/api/nurses")
@RequiredArgsConstructor
public class NurseApplicationController {

    private final NurseApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<?> apply(@ModelAttribute @Valid NurseApplicationRequest request) {
        applicationService.apply(request);
        return ResponseEntity.ok("Application submitted");
    }
}
