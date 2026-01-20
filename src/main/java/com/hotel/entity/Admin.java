package com.hotel.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String fname;
    private String lname;
    private String gender;
    private String dob;
    private String contact;
    private String address;
    private String image;
    private String created_at;
}