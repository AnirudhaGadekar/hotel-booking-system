package com.hotel.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String customerName;    // ← Store customer name directly

    @Column(name = "roomname")
    private String roomNumber;      // ← Store room number directly

    private Integer kidno;
    private Integer adultno;
    private LocalDate fromdate;
    private LocalDate todate;

    private Integer taxamount;
    private Integer totalamount;
    private Integer paid;
    
    @Column(name = "created_date")
    private LocalDate createdDate;
}
