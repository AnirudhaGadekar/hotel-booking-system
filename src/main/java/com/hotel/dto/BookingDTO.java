package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Integer id;
    private String customerName;
    private String roomNumber;
    private LocalDate fromdate;
    private LocalDate todate;
    private String bookingStatus;
    private Integer totalamount;
}
