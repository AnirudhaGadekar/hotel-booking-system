package com.hotel.service.impl;

import com.hotel.dto.DashboardStats;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.CustomerRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public DashboardStats getStats() {
        // This runs the "SELECT COUNT(*)" queries automatically
        long totalRooms = roomRepository.count();
        long totalCustomers = customerRepository.count();
        long totalBookings = bookingRepository.count();

        return new DashboardStats(totalRooms, totalCustomers, totalBookings);
    }
}