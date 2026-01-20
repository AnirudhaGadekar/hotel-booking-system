package com.hotel.repository;

import com.hotel.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // 1. Added Import
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
    /**
     * CHANGE 1: Added COALESCE to handle NULL values
     * CHANGE 2: Used @Param for safer variable binding
     */
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.bookingid = :bookingId")
    Integer sumAmountByBookingId(@Param("bookingId") Integer bookingId);
    
    // CHANGE 3: Kept 'findByBookingid' (lowercase 'i') because your Entity field is 'bookingid'
    List<Payment> findByBookingid(Integer bookingid);
    
    // Matches your 'datee' column in database
    List<Payment> findByDateeBetween(LocalDate fromDate, LocalDate toDate);
}