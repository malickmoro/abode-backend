package com.morosa.abode.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "NURSE")
public class Nurse extends EntityModel {

    private String firstName;
    private String surname;
    private String email;
    private String phone;
    private String password;

    @JoinColumn(name = "LOCATION")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Location location;

    @Column(name = "PROFILE_PHOTO_URL")
    private String profilePhotoUrl;

    @ElementCollection
    private List<String> services;

    private Double rating = 0.0;

    private LocalDateTime lastLoggedIn;
}
