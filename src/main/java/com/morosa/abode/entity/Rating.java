/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author plutus
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RATINGS")
public class Rating extends EntityModel {

    @ManyToOne
    @JoinColumn(name = "NURSE_ID", nullable = false)
    private Nurse nurse;

    private double rating; // e.g., 4.5

    private String feedback;

    private String reviewerName;
}
