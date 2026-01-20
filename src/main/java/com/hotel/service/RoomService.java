package com.hotel.service;

import com.hotel.entity.Room;
import com.hotel.dto.RoomRequest;

public interface RoomService {
    Room createRoom(RoomRequest request);
    Room updateRoom(Integer id, RoomRequest request);
    void deleteRoom(Integer id);
}
