package com.hotel.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_email_config")
public class EmailConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_id") // PHP Primary Key is e_id
    private Integer id;

    private String name;
    private String mail_driver_host;
    private String mail_port;
    private String mail_username;
    private String mail_password;
    private String mail_encrypt;
}