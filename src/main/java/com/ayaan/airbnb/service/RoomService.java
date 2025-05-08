package com.ayaan.airbnb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayaan.airbnb.model.Room;
import com.ayaan.airbnb.repository.RoomRepository;

@Service
public class RoomService {
    
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getRoomById(int id) {
        return roomRepository.findById(id).orElse(null);
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    
    // public List<Room> getRoomsByUserId(int userId) {
    //     return roomRepository.findByUserId(userId);
    // }
}
