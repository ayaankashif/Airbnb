package com.ayaan.airbnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ayaan.airbnb.model.Reservation;
import com.ayaan.airbnb.model.User;
import com.ayaan.airbnb.service.HotelService;
import com.ayaan.airbnb.service.ReservationService;
import com.ayaan.airbnb.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ReservationController {

    private final HotelService hotelService;
    private final ReservationService reservationService;
    private final UserService userService;

    public ReservationController(ReservationService reservationService, HotelService hotelService, UserService userService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.hotelService = hotelService;
    }

    @GetMapping("/reserve")
    public String showReservationForm(@RequestParam("userId") Integer id, Model model) {
        Reservation reservation = new Reservation();
        User user = userService.getUserById(id);
        reservation.setUser(user);
        model.addAttribute("reserve", reservation);
        return "reservation"; 
    }

    @PostMapping("/reservation")
    public String submitReservation(@ModelAttribute("reserve") Reservation reservation) {
        Integer userId = reservation.getUser() != null ? reservation.getUser().getUserId() : null;
        if (userId != null) {
            User user = userService.getUserById(userId);
            reservation.setUser(user); 
            reservationService.saveReservation(reservation);
        }
        return "redirect:/reserve"; 
    }

    @GetMapping("/reserve/hotel")
    public String showHotel (Model model) {


        model.addAttribute("hotels", hotelService.getAllHotels());
        return "hotel";
    }
    
}
