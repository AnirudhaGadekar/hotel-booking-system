package com.hotel.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "manage_website")
public class ManageWebsite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String short_title;
    private String logo;
    private String footer;
    private String currency_code;
    private String currency_symbol;
    private String login_image;
    private String invoice_logo;
    private String background_login_image;
}