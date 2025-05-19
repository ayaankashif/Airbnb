package com.ayaan.airbnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ayaan.airbnb.model.Amenities;
import com.ayaan.airbnb.model.Room;
import com.ayaan.airbnb.service.AmenitiesService;
import com.ayaan.airbnb.service.HotelService;
import com.ayaan.airbnb.service.RoomService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class AmenitiesController {
    private final RoomService roomService;
    private final HotelService hotelService;
    private final AmenitiesService amenitiesService;

    public AmenitiesController(RoomService roomService, HotelService hotelService, AmenitiesService amenitiesService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
        this.amenitiesService = amenitiesService;
    }

    @GetMapping("/amenities/register")
    public String showAmenitiesRegistrationForm(@RequestParam("roomId") Integer id, Model model) {
        Room room = roomService.getRoomById(id);
        if (room == null) {
            return "redirect:/room/register";
        }
        Amenities amenities = new Amenities(); 
        amenities.setRoom(room);
        model.addAttribute("amenity", new Amenities());
        model.addAttribute("amenities", amenitiesService.getAllAmenities());
        return "amenities";
    }

    @PostMapping("/amenities")
    public String postMethodName(@ModelAttribute("amenity") Amenities amenity) {
        Integer roomId = amenity.getRoom() != null ? amenity.getRoom().getRoomId() : null;
        if (roomId != null) {
            Room room = roomService.getRoomById(roomId);
            amenity.setRoom(room); 
            amenitiesService.saveAmenities(amenity);
        }
        return "redirect:/amenities/register?roomId=" + roomId;
    }


}
