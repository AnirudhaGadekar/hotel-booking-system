package com.hotel.repository;

import com.hotel.dto.BookingDTO;
import com.hotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    
    // For report_booking.php
    List<Booking> findByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);
    
    // ✅ SIMPLE: Just get raw Booking entities, convert in service
    @Query("SELECT b FROM Booking b")
    List<Booking> getAllBookingsForDTO();
}
