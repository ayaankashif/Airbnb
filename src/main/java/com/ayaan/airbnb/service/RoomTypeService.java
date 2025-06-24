// package com.ayaan.airbnb.service;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.ayaan.airbnb.model.RoomType;
// import com.ayaan.airbnb.repository.RoomTypeRepository;

// @Service
// public class RoomTypeService {

//     private final RoomTypeRepository roomTypeRepository;

//     public RoomTypeService(RoomTypeRepository roomTypeRepository) {
//         this.roomTypeRepository = roomTypeRepository;
//     }

//     public RoomType getRoomTypeById(Integer id) {
//         return roomTypeRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Room type not found with id: " + id));
//     }
    
//     public RoomType createRoomType(RoomType roomType) {
//         return roomTypeRepository.save(roomType);
//     }

//     public RoomType updateRoomType(RoomType roomType) {
//         return roomTypeRepository.save(roomType);
//     }

//     public void deleteRoomType(Integer id) {
//         roomTypeRepository.deleteById(id);
//     }

//     public List<RoomType> getAllRoomType() {
//         return roomTypeRepository.findAll();
//     }
    
//     public List<RoomType> getRoomTypeByName(String RoomType) {
//         return roomTypeRepository.findByRoomType(RoomType);
//     }
// }
