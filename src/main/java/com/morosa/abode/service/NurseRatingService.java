/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.service;

import com.morosa.abode.entity.Nurse;
import com.morosa.abode.entity.Rating;
import com.morosa.abode.exceptions.NurseNotFoundException;
import com.morosa.abode.payload.request.NurseRatingRequest;
import com.morosa.abode.repository.NurseRepository;
import com.morosa.abode.repository.RatingRepository;
import java.util.List;
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
public class NurseRatingService {

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public void submitRating(NurseRatingRequest request) {
        Optional<Nurse> found = nurseRepository.findById(request.getNurseId());
        if (found.isEmpty()) {
            throw new NurseNotFoundException("Nurse with provided id not found");
        }

        Nurse nurse = found.get();
        createRatingAndLinkToNurse(nurse, request);
    }

    private void createRatingAndLinkToNurse(Nurse nurse, NurseRatingRequest request) {
        Rating rating = new Rating();
        rating.setNurse(nurse);
        rating.setFeedback(request.getFeedback());
        rating.setRating(request.getRating());
        rating.setReviewerName(request.getReviewerName());
        ratingRepository.save(rating);

        // Recalculate average
        List<Rating> allRatings = ratingRepository.findByNurse(nurse);
        double average = allRatings.stream()
                .mapToDouble(Rating::getRating)
                .average()
                .orElse(0.0);

        nurse.setAverageRating(average);
        nurseRepository.save(nurse);
    }

}
