package com.ayaan.airbnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ayaan.airbnb.model.Hotel;
import com.ayaan.airbnb.service.HotelService;
import com.ayaan.airbnb.service.UserService;

@Controller
public class HotelController {
    
    private final HotelService hotelService;
    private final UserService userService;
 
    public HotelController(HotelService hotelService, UserService userService) {
        this.userService = userService;
        this.hotelService = hotelService;
    }

    @GetMapping({"/hotel/register", "/hotel/edit/{id}"})
    public String viewOrEditHotel(@PathVariable(required = false) Integer id, Model model) {
        if (id != null) {
            Hotel editHotel = hotelService.getHotelById(id);
            model.addAttribute("editHotel", editHotel);
        }
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("hotels", hotelService.getAllHotels()); 
        return "hotel";
    }

    @PostMapping("/hotel")
    public String registerHotel(@ModelAttribute("hotel") Hotel hotel) {
        // String username = principal.getName();
        // User user = userService.getUserByUsername(username);
        // hotel.setUser(user);
        hotelService.saveHotel(hotel);
        // return "redirect:/room/register?hotelId=" + hotel.getHotelId();
        return "redirect:/hotel/register";
    }
       
    @PostMapping("/hotel/edit/{id}")
    public String updateHotel(@PathVariable("id") Integer id, @ModelAttribute("editHotel") Hotel hotel) {
        Hotel existing = hotelService.getHotelById(id);
        existing.setHotelName(hotel.getHotelName());
        existing.setHotelType(hotel.getHotelType());
        existing.setAddress(hotel.getAddress());
        existing.setCity(hotel.getCity());

        hotelService.updateHotel(existing);
        return "redirect:/hotel/register";
    }

    @GetMapping("/hotel/delete/{id}")
    public String deleteHotel(@PathVariable("id") Integer id) {
        hotelService.deleteHotel(id);
        return "redirect:/hotel/register";   
    }
}
