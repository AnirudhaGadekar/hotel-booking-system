package com.hotel.controller;

import com.hotel.dto.ApiResponse;
import com.hotel.dto.RoomRequest;
import com.hotel.entity.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private RoomRepository roomRepository;
    
    /**
     * GET ALL ROOMS (view_room.php)
     * URL: GET /api/rooms
     */
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomRepository.findAll());
    }
    
    /**
     * GET SINGLE ROOM (for edit form)
     * URL: GET /api/rooms/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable Integer id) {
        return roomRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * CREATE ROOM (add_room.php → pages/save_room.php)
     * URL: POST /api/rooms
     */
    @PostMapping
    public ResponseEntity<ApiResponse> createRoom(@RequestBody RoomRequest request) {
        Room room = roomService.createRoom(request);
        return ResponseEntity.ok(ApiResponse.success("Room Added Successfully", room));
    }
    
    /**
     * UPDATE ROOM (edit_room.php)
     * URL: PUT /api/rooms/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateRoom(@PathVariable Integer id, @RequestBody RoomRequest request) {
        Room room = roomService.updateRoom(id, request);
        return ResponseEntity.ok(ApiResponse.success("Record Successfully Updated", room));
    }
    
    /**
     * DELETE ROOM (del_room.php)
     * URL: DELETE /api/rooms/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok(ApiResponse.success("Record Successfully Deleted", null));
    }
}