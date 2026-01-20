package com.hotel.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer bookingid;

    @Column(nullable = false)
    private Integer amount; // Matches int(11)

    @Column(nullable = false)
    private LocalDate datee;
}