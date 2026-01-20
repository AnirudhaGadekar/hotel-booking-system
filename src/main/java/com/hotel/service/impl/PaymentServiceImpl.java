package com.hotel.service.impl;

import com.hotel.dto.PaymentRequest;
import com.hotel.entity.Booking;
import com.hotel.entity.Payment;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.PaymentRepository;
import com.hotel.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    @Transactional
    public Payment createPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setBookingid(request.getBookingid());
        payment.setAmount(request.getAmount()); // Integer
        payment.setDatee(request.getDatee());
        Payment saved = paymentRepository.save(payment);

        // Update Booking
        Integer totalPaid = paymentRepository.sumAmountByBookingId(request.getBookingid());
        if (totalPaid == null) totalPaid = 0;

        Booking booking = bookingRepository.findById(request.getBookingid()).orElseThrow();
        booking.setPaid(totalPaid);
        bookingRepository.save(booking);

        return saved;
    }
    
    @Override
    public Integer getTotalPaidForBooking(Integer bookingId) {
        Integer val = paymentRepository.sumAmountByBookingId(bookingId);
        return val != null ? val : 0;
    }
}