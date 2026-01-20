package com.hotel.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PaymentRequest {
    private Integer bookingid;
    private Integer amount; // Integer input
    private LocalDate datee;
}