package com.hotel.service;

import com.hotel.dto.BookingRequest;
import com.hotel.dto.BookingDTO;
import com.hotel.entity.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequest request);
    Booking updateBooking(Integer id, BookingRequest request);
    void deleteBooking(Integer id);
    Booking getBookingById(Integer id);
    List<Booking> getAllBookings();
    
    // ✅ NEW METHODS
    List<BookingDTO> getAllBookingsDTO();
    BookingDTO getBookingByIdDTO(Integer id);
    Integer getPaidAmount(Integer bookingId);
}
