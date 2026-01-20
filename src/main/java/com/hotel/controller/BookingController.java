package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.dto.BookingRequest;
import com.hotel.dto.BookingDTO;
import com.hotel.entity.Booking;
import com.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<ApiResponse> createBooking(@RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(request);
        return ResponseEntity.ok(ApiResponse.success("Booking Created Successfully", booking));
    }

    // ✅ Returns DTO with customer and room names
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookingsDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingService.getBookingByIdDTO(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBooking(@PathVariable Integer id, @RequestBody BookingRequest request) {
        Booking booking = bookingService.updateBooking(id, request);
        return ResponseEntity.ok(ApiResponse.success("Booking Updated Successfully", booking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBooking(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok(ApiResponse.success("Booking Deleted Successfully", null));
    }

    @GetMapping("/{id}/paid-amount")
    public ResponseEntity<Integer> getPaidAmount(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingService.getPaidAmount(id));
    }
}
