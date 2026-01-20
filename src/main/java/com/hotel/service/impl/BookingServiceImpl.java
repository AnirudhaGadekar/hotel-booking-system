package com.hotel.service.impl;

import com.hotel.dto.BookingDTO;
import com.hotel.dto.BookingRequest;
import com.hotel.entity.Booking;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.CustomerRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Booking createBooking(BookingRequest request) {
        Booking booking = new Booking();
        return saveBookingLogic(booking, request);
    }

    @Override
    public Booking updateBooking(Integer id, BookingRequest request) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        return saveBookingLogic(booking, request);
    }

    private Booking saveBookingLogic(Booking booking, BookingRequest request) {
        String[] roomParts = request.getRoomname().split(",");
        String roomId = roomParts[0];
        int adultPrice = Integer.parseInt(roomParts[1]);
        int kidPrice = Integer.parseInt(roomParts[2]);

        String[] taxParts = request.getTax().split(",");
        int taxPercent = Integer.parseInt(taxParts[1]);

        long days = ChronoUnit.DAYS.between(request.getFromdate(), request.getTodate());
        if (days < 1) days = 1;

        int dailyCost = (request.getKidno() * kidPrice) + (request.getAdultno() * adultPrice);
        int totalRaw = (int) (dailyCost * days);
        int taxRaw = totalRaw + (totalRaw * taxPercent / 100);

        booking.setCustomerName(request.getName());
        booking.setRoomNumber(roomId);
        booking.setKidno(request.getKidno());
        booking.setAdultno(request.getAdultno());
        booking.setFromdate(request.getFromdate());
        booking.setTodate(request.getTodate());
        booking.setTotalamount(totalRaw);
        booking.setTaxamount(taxRaw);
        
        if (booking.getId() == null) {
            booking.setPaid(0);
            booking.setCreatedDate(LocalDate.now());
        }

        return bookingRepository.save(booking);
    }

    @Override
    public List<BookingDTO> getAllBookingsDTO() {
        return bookingRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingByIdDTO(Integer id) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        return convertToDTO(booking);
    }

    @Override
    public Booking getBookingById(Integer id) {
        return bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Integer getPaidAmount(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
        return booking.getPaid() != null ? booking.getPaid() : 0;
    }

    @Override
    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }

    private BookingDTO convertToDTO(Booking booking) {
        String customerName = booking.getCustomerName();
        String roomNumber = booking.getRoomNumber();

        // Fetch customer name by ID
        if (customerName != null && customerName.matches("\\d+")) {
            try {
                Integer customerId = Integer.parseInt(customerName);
                var customer = customerRepository.findById(customerId);
                if (customer.isPresent()) {
                    customerName = customer.get().getName();
                }
            } catch (Exception e) {
                // Keep original value
            }
        }

        // Fetch room name by ID
        if (roomNumber != null && roomNumber.matches("\\d+")) {
            try {
                Integer roomId = Integer.parseInt(roomNumber);
                var room = roomRepository.findById(roomId);
                if (room.isPresent()) {
                    roomNumber = room.get().getRoomname();
                }
            } catch (Exception e) {
                // Keep original value
            }
        }

        return new BookingDTO(
            booking.getId(),
            customerName != null ? customerName : "Unknown",
            roomNumber != null ? roomNumber : "Unassigned",
            booking.getFromdate(),
            booking.getTodate(),
            "pending",
            booking.getTotalamount()
        );
    }
}
