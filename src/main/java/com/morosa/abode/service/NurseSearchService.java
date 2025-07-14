/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.service;

import com.morosa.abode.dto.NurseSearchDTO;
import com.morosa.abode.entity.Location;
import com.morosa.abode.payload.request.SearchRequest;
import com.morosa.abode.repository.LocationRepository;
import com.morosa.abode.repository.NurseRepository;
import com.morosa.abode.utils.Functions;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author plutus
 */
@Service
@RequiredArgsConstructor
public class NurseSearchService {

    @Autowired
    private NurseRepository nurseRepository;

    public List<NurseSearchDTO> search(SearchRequest request) {
        return nurseRepository.findByAnyService(request.getServices())
                .stream()
                .filter(nurse -> {
                    Location loc = nurse.getLocation();
                    return Functions.haversine(request.getLatitude(), request.getLongitude(), loc.getLatitude(), loc.getLongitude()) <= request.getMaxDistanceKm();
                })
                .map(NurseSearchDTO::from)
                .collect(Collectors.toList());

    }
}
