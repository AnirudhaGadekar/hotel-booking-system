package com.hotel.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data @Entity @Table(name = "tbl_customer")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String gender;
    private LocalDate birthdate; // Must match setBirthdate
    private String contact;      // Must match setContact
    private String address;
    @Column(name = "created_date")
    private LocalDate createdDate;
}