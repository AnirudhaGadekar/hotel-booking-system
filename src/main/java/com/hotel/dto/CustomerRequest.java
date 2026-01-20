package com.hotel.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CustomerRequest {
    private String name;
    private String email;
    private String gender;
    private LocalDate birthdate;
    private String contact;
    private String address;
}