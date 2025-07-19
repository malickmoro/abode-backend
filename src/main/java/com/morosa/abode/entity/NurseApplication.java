/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.entity;

import com.morosa.abode.entity.enums.ApplicationStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
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
@Table(name = "APPLICATIONS")
public class NurseApplication extends EntityModel {

    private String applicationId;

    @OneToOne
    @JoinColumn(nullable = true, name = "NURSE")
    private Nurse nurse;

    private String firstName;
    private String surname;
    private String email;
    private String phone;
    private LocalDate dob;

    @JoinColumn(name = "LOCATION")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Location location;

    @Column(name = "PHOTO_URL")
    private String photoUrl;

    @Column(name = "ID_CARD_URL")
    private String idCardUrl;

    @Column(name = "NSS-CERTIFICATE_URL")
    private String nssCertificateUrl;

    @ElementCollection
    private List<String> services;

    @Column(name = "APPLICATION_STATUS")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @Column(name = "REASON")
    private String rejectionReason;

}
