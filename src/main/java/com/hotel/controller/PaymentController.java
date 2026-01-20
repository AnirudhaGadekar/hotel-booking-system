package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.dto.PaymentRequest;
import com.hotel.entity.Payment;
import com.hotel.entity.Booking;
import com.hotel.repository.PaymentRepository;
import com.hotel.repository.BookingRepository;
import com.hotel.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    /**
     * GET ALL PAYMENTS
     * URL: GET /api/payments
     */
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentRepository.findAll());
    }
    
    /**
     * GET PAYMENTS BY BOOKING ID (edit_payment.php)
     * URL: GET /api/payments/booking/{bookingId}
     */
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<Payment>> getPaymentsByBooking(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(paymentRepository.findByBookingid(bookingId));
    }
    
    /**
     * CREATE PAYMENT (edit_payment.php form submission)
     * URL: POST /api/payments
     */
    @PostMapping
    public ResponseEntity<ApiResponse> createPayment(@RequestBody PaymentRequest request) {
        // Validate amount doesn't exceed remaining balance
        Booking booking = bookingRepository.findById(request.getBookingid())
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        Integer paidAmount = paymentRepository.sumAmountByBookingId(request.getBookingid());
        paidAmount = paidAmount != null ? paidAmount : 0;
        
        int remaining = booking.getTaxamount() - paidAmount;
        
        if (request.getAmount() > remaining) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.error("Amount exceeds remaining balance"));
        }
        
        Payment payment = paymentService.createPayment(request);
        return ResponseEntity.ok(ApiResponse.success("Payment Added Successfully", payment));
    }
    
    /**
     * DELETE PAYMENT
     * URL: DELETE /api/payments/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePayment(@PathVariable Integer id) {
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Payment not found"));
        
        Integer bookingId = payment.getBookingid();
        paymentRepository.deleteById(id);
        
        // Update booking paid amount after deletion
        Integer totalPaid = paymentRepository.sumAmountByBookingId(bookingId);
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setPaid(totalPaid != null ? totalPaid : 0);
        bookingRepository.save(booking);
        
        return ResponseEntity.ok(ApiResponse.success("Payment Deleted Successfully", null));
    }
}
