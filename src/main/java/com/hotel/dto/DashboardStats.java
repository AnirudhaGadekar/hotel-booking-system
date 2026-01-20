package com.hotel.dto;

public class DashboardStats {
    private long totalRooms;
    private long totalCustomers;
    private long totalBookings;

    // ✅ Constructor (CRITICAL: This allows the Service to fill the data)
    public DashboardStats(long totalRooms, long totalCustomers, long totalBookings) {
        this.totalRooms = totalRooms;
        this.totalCustomers = totalCustomers;
        this.totalBookings = totalBookings;
    }

    // ✅ Getters (CRITICAL: This allows the Frontend to read the data)
    public long getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(long totalRooms) {
        this.totalRooms = totalRooms;
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }
}