package com.ayaan.airbnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ayaan.airbnb.model.Amenities;
import com.ayaan.airbnb.model.Room;
import com.ayaan.airbnb.service.AmenitiesService;
import com.ayaan.airbnb.service.RoomService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AmenitiesController {
    private final RoomService roomService;
    private final AmenitiesService amenitiesService;

    public AmenitiesController(RoomService roomService, AmenitiesService amenitiesService) {
        this.roomService = roomService;
        this.amenitiesService = amenitiesService;
    }

    @GetMapping("/amenities/register")
    public String showAmenitiesRegistrationForm(@RequestParam("roomId") Integer id, Model model) {
        Room room = roomService.getRoomById(id);
        if (room == null) {
            return "redirect:/hotel/register";
        }
        Amenities amenities = new Amenities(); 
        amenities.setRoom(room);
        model.addAttribute("amenity", amenities);
        model.addAttribute("amenities", amenitiesService.getAmenitiesByRoomId(id));
        return "amenities";
    }

    @PostMapping("/amenities")
    public String postMethodName(@ModelAttribute("amenity") Amenities amenity) {
        Integer roomId = amenity.getRoom() != null ? amenity.getRoom().getRoomId() : null;
        if (roomId != null) {
            Room room = roomService.getRoomById(roomId);
            amenity.setRoom(room); 
            amenitiesService.saveAmenities(amenity);
            return "redirect:/amenities/register?roomId=" + roomId;
        }
        return "redirect:/hotel/register";
    }
}
