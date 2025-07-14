/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author plutus
 */
@Getter
@Setter
public class NurseRatingRequest {

    private String nurseId;
    @Min(1)
    @Max(5)
    private int rating;
    private String feedback;
    private String reviewerName;
}
