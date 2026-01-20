package com.hotel.service.impl;

import com.hotel.dto.RoomRequest;
import com.hotel.entity.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Transactional
    public Room createRoom(RoomRequest request) {
        Room room = new Room();
        room.setFloorno(request.getFloorno());
        room.setRoomname(request.getRoomname());
        room.setPeradultprice(request.getPeradultprice());
        room.setPerkidprice(request.getPerkidprice());
        room.setColor(request.getColor());  // ← Use color field
        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public Room updateRoom(Integer id, RoomRequest request) {
        Room room = roomRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Room not found"));
        room.setFloorno(request.getFloorno());
        room.setRoomname(request.getRoomname());
        room.setPeradultprice(request.getPeradultprice());
        room.setPerkidprice(request.getPerkidprice());
        room.setColor(request.getColor());  // ← Use color field
        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }
}
