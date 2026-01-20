package com.hotel.dto;

import lombok.Data;

@Data
public class RoomRequest {
    private Integer floorno;
    private String roomname;
    private Integer peradultprice;
    private Integer perkidprice;
    private String color;  // Used for amenities in the original code
}
