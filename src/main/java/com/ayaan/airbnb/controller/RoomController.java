package com.ayaan.airbnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ayaan.airbnb.model.Hotel;
import com.ayaan.airbnb.model.Room;
import com.ayaan.airbnb.service.HotelService;
import com.ayaan.airbnb.service.RoomService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RoomController {

    private final RoomService roomService;
    private final HotelService hotelService;

    public RoomController(RoomService roomService, HotelService hotelService) {     
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @GetMapping("/room/register")
    public String showRoomRegistrationForm(@RequestParam("hotelId") Integer id, Model model)  {   
        Room room = new Room();
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel == null) {
            model.addAttribute("error", "No hotel available. Register your hotel first."); 
            return "redirect:/hotel/register";
        }
        model.addAttribute("rooms", roomService.getRoomsByHotelId(id));
        room.setHotel(hotel); 
        model.addAttribute("room", room);
        return "room";
    }
    
    @PostMapping("/room")
    public String postMethodName(@ModelAttribute("room") Room room) {
        Integer hotelId = room.getHotel() != null ? room.getHotel().getHotelId() : null;
        if (hotelId != null) {
            Hotel hotel = hotelService.getHotelById(hotelId);
            room.setHotel(hotel); 
            roomService.saveRoom(room);
        }
        return "redirect:/room/register?hotelId=" + hotelId;
    }

    @GetMapping("/room/edit/{id}")
    public String editRoom(@PathVariable("id") Integer id, Model model) {
        Integer hotelId = roomService.getRoomById(id).getHotel().getHotelId();
        Room editRoom = roomService.getRoomById(id);
        model.addAttribute("editRoom", editRoom);
        model.addAttribute("rooms", roomService.getRoomsByHotelId(hotelId));
        model.addAttribute("room", new Room());
        return "room";
    }
    
    @PostMapping("/room/edit/{id}")
    public String updateRoom(@PathVariable("id") Integer id, @ModelAttribute("editRoom") Room room) {
        Room existing = roomService.getRoomById(id);
        existing.setRoomType(room.getRoomType());
        existing.setSummary(room.getSummary());
        existing.setPrice(room.getPrice());
        
        roomService.updateRoom(existing);
        return "redirect:/room/register?hotelId=" + existing.getHotel().getHotelId();
    }

    @GetMapping("/room/delete/{id}")
    public String deleteRoom(@PathVariable("id") Integer id) {
        Room room = roomService.getRoomById(id);
        Integer hotelId = room.getHotel().getHotelId();
        roomService.deleteRoom(id);
        return "redirect:/room/register?hotelId=" + hotelId;   
    }
}
