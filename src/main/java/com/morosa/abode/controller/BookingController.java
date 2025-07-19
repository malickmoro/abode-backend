/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.controller;

import com.morosa.abode.entity.enums.BookingStatus;
import com.morosa.abode.payload.request.BookingRequest;
import com.morosa.abode.payload.response.ApiReponseWithList;
import com.morosa.abode.payload.response.BookingResponse;
import com.morosa.abode.service.BookingService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author plutus
 */
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<?> bookNurse(@RequestBody @Valid BookingRequest request) {
        bookingService.createBooking(request);
        return ResponseEntity.ok("Booking created successfully");
    }

    @GetMapping("/nurse/{id}")
    public ResponseEntity<?> getNurseBookings(@PathVariable String id) {
        List<BookingResponse> response = bookingService.getBookingsForNurse(id);
        return ResponseEntity.ok(new ApiReponseWithList("00", true, response));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable String id, @RequestParam BookingStatus status) {
        bookingService.updateStatus(id, status);
        return ResponseEntity.ok("Booking status updated");
    }
}
