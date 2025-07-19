package com.morosa.abode.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dob;
    @JoinColumn(name = "LOCATION")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Location location;

    @Column(name = "PROFILE_PHOTO_URL")
    private String profilePhotoUrl;

    @Column(name = "RESET_PASSWORD")
    private boolean resetPassword;

    @ElementCollection
    @CollectionTable(name = "nurse_services", joinColumns = @JoinColumn(name = "nurse_id"))
    @Column(name = "services")
    private List<String> services;

    @OneToMany(mappedBy = "nurse")
    private List<Rating> ratings;

    private double averageRating = 0;

    private LocalDateTime lastLoggedIn;
    
    public String getFullname(){
        return firstName + " " + surname; 
    }
}
