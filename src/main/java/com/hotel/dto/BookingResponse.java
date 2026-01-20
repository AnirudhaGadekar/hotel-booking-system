package com.hotel.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingResponse {
    private Integer id;
    private String customerName;
    private String roomName;
    private Integer kidno;
    private Integer adultno;
    private LocalDate fromdate;
    private LocalDate todate;
    private Integer totalamount;
    private Integer taxamount;
    private Integer paidAmount;
    private String status;
}