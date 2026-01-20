package com.hotel.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_rooms")
public class Room {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    
	    private String roomname;
	    private Integer peradultprice;
	    private Integer perkidprice;
	    private Integer floorno;

    @Column(name = "color")
    private String color;                  // ← Amenities
}
