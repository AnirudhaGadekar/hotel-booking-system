package com.hotel.service;

import com.hotel.entity.Payment;
import com.hotel.dto.PaymentRequest;

public interface PaymentService {
    Payment createPayment(PaymentRequest request);
    
    // THIS WAS MISSING:
    Integer getTotalPaidForBooking(Integer bookingId);
}