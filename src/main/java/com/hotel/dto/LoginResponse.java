package com.hotel.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private boolean success;
    private String message;
    private Integer id;
    private String username;
    private String email;
    private String fname;
    private String lname;
    private String image;
}
