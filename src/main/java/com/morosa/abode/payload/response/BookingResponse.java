/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.payload.response;

import com.morosa.abode.entity.Booking;
import com.morosa.abode.entity.enums.BookingStatus;
import com.morosa.abode.entity.enums.PaymentMethod;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author plutus
 */
@Getter @Setter
public class BookingResponse {
    private String id;
    private String nurseName;
    private String customerName;
    private String serviceType;
    private LocalDateTime appointmentDate;
    private BookingStatus status;
    private PaymentMethod paymentMethod;
    private String notes;

    public static BookingResponse from(Booking b) {
        BookingResponse dto = new BookingResponse();
        dto.setId(b.getId());
        dto.setNurseName(b.getNurse().getFullname());
        dto.setCustomerName(b.getCustomerName());
        dto.setServiceType(b.getServiceType());
        dto.setAppointmentDate(b.getAppointmentDate());
        dto.setStatus(b.getStatus());
        dto.setPaymentMethod(b.getPaymentMethod());
        dto.setNotes(b.getNotes());
        return dto;
    }
}
