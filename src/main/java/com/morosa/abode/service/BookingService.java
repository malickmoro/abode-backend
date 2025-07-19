/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.service;

import com.morosa.abode.entity.Booking;
import com.morosa.abode.entity.Nurse;
import com.morosa.abode.entity.enums.BookingStatus;
import com.morosa.abode.exceptions.NurseNotFoundException;
import com.morosa.abode.payload.request.BookingRequest;
import com.morosa.abode.payload.response.BookingResponse;
import com.morosa.abode.repository.BookingRepository;
import com.morosa.abode.repository.NurseRepository;
import com.morosa.abode.utils.Functions;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author plutus
 */
@Service
@RequiredArgsConstructor
public class BookingService {

    private final NurseRepository nurseRepository;
    private final BookingRepository bookingRepository;

    public void createBooking(BookingRequest req) {
        Nurse nurse = nurseRepository.findById(req.getNurseId())
                .orElseThrow(() -> new NurseNotFoundException("Nurse not found"));

        Booking booking = new Booking();
        booking.setNurse(nurse);
        booking.setCustomerName(req.getCustomerName());
        booking.setCustomerPhone(req.getCustomerPhone());
        booking.setServiceType(req.getServiceType());
        booking.setAppointmentDate(req.getScheduledTime());
        booking.setPaymentMethod(req.getPaymentMethod());
        booking.setStatus(BookingStatus.PENDING);
        booking.setNotes(req.getNotes());
        booking.setBookingCode(Functions.generateBookingCode());

        bookingRepository.save(booking);
    }

    public List<BookingResponse> getBookingsForNurse(String nurseId) {
        return bookingRepository.findByNurseId(nurseId)
                .stream()
                .map(BookingResponse::from)
                .toList();
    }

    public void updateStatus(String bookingId, BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        booking.setStatus(status);
        bookingRepository.save(booking);
    }
}

