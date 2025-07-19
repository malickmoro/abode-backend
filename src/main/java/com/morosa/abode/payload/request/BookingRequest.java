/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.payload.request;

import com.morosa.abode.entity.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author plutus
 */
@Getter @Setter
public class BookingRequest {
    @NotBlank private String nurseId;
    @NotBlank private String customerName;
    @NotBlank private String customerPhone;
    @NotBlank private String serviceType;
    @NotNull private LocalDateTime scheduledTime;
    @NotNull private PaymentMethod paymentMethod;
    private String notes;
}
