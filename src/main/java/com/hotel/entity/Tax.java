package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_tax")
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "taxname") // Matches PHP column
    private String taxname;   // Fixes "setTaxname undefined" error

    private Integer percentage; // Changed String -> Integer to fix type error
    private String shortcode;
}