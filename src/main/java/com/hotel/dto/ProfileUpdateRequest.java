package com.hotel.dto;

import lombok.Data;

@Data
public class ProfileUpdateRequest {
    private String fname;
    private String lname;
    private String email;
    private String contact;
    private String dob;
    private String gender;
}