package com.hotel.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequest {
    private String name;      // Customer ID
    private String roomname;  // Format: "id,adultPrice,kidPrice"
    private String tax;       // Format: "id,percentage,name"
    private Integer kidno;
    private Integer adultno;
    private LocalDate fromdate;
    private LocalDate todate;
}