package com.hotel.controller;

import com.hotel.entity.Booking;
import com.hotel.entity.Payment;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // URL: /api/reports/bookings?from=2023-01-01&to=2023-12-31
    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getBookingReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(bookingRepository.findByCreatedDateBetween(from, to));
    }

    // URL: /api/reports/payments?from=2023-01-01&to=2023-12-31
    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getPaymentReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(paymentRepository.findByDateeBetween(from, to));
    }
}