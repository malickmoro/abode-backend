/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author plutus
 */
@Data
public class NurseApplicationRequest {
    @NotBlank(message = "First Name is required")
    private String firstName;
    
    @NotBlank(message = "Surname is required")
    private String surname;
    
    @NotBlank(message = "Email address is required")
    @Email(message = "Please enter a valid email")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    private String phone;
    
    @NotBlank(message = "GPS address is required")
    private String gpsAddress; 
    
    private String dob;
  
    @NotEmpty(message = "At least one service must be selected")
    private List<String> services;

    private MultipartFile idCard;
    private MultipartFile nssCertificate;
    private MultipartFile photo;
}
