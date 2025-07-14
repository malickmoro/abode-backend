/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.controller;

import com.morosa.abode.dto.NurseSearchDTO;
import com.morosa.abode.payload.request.SearchRequest;
import com.morosa.abode.payload.response.ApiReponseWithList;
import com.morosa.abode.service.NurseSearchService;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("/api/nurses")
@RequiredArgsConstructor
public class SearchController {

    private final NurseSearchService searchService;

    @PostMapping("/search")
    public ResponseEntity<?> searchNurses(@RequestBody @Valid SearchRequest request) {
        List<NurseSearchDTO> results = searchService.search(request);
        return ResponseEntity.ok(new ApiReponseWithList("00", true, results));
    }
}

