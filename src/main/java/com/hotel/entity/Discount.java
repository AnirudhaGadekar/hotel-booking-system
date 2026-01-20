package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String discountname;
    private Integer percentage;    // Changed String -> Integer
    private LocalDate expirydate;  // Changed String -> LocalDate
}