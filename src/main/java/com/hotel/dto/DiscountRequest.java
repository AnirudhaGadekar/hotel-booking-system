package com.hotel.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DiscountRequest {
    private String discountname;
    private Integer percentage;
    private LocalDate expirydate;
}