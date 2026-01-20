package com.hotel.dto;

import lombok.Data;

@Data
public class TaxRequest {
    private String taxname;
    private Integer percentage;
    private String shortcode;
}
