/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.entity;

import com.morosa.abode.entity.enums.BookingStatus;
import com.morosa.abode.entity.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "NURSE_BOOKINGS")
public class Booking extends EntityModel {

    @ManyToOne
    @JoinColumn(name = "nurse_id", nullable = false)
    private Nurse nurse;

    private String userId;

    private String service;

    private double amount;

    private double tip;

    @Enumerated(EnumType.STRING)
    private BookingStatus status; // PENDING, CONFIRMED, COMPLETED

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus; // HELD, RELEASED
}
