package com.ayaan.airbnb.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ayaan.airbnb.model.Hotel;
import com.ayaan.airbnb.model.Reservation;
import com.ayaan.airbnb.model.Room;
import com.ayaan.airbnb.model.User;
import com.ayaan.airbnb.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@SessionAttributes("reservation")
public class ReservationController {

    private final AmenitiesService amenitiesService;

    private final RoomService roomService;
    private final HotelService hotelService;
    private final ReservationService reservationService;
    private final UserService userService;


    public ReservationController(ReservationService reservationService, HotelService hotelService, UserService userService, RoomService roomService, AmenitiesService amenitiesService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.amenitiesService = amenitiesService;
    }

    @ModelAttribute("reservation")
    public Reservation createReservation() {
        return new Reservation();  
    }

    @GetMapping("/confirmation")
    public String showConfirmation() {
        return "confirmation"; 
    }

    @GetMapping("/reserve")
    public String showReservationForm(Model model) {
        List<String> cities = hotelService.getAllHotels().stream().map(Hotel::getCity).distinct().collect(Collectors.toList());
        model.addAttribute("cities", cities);   
        Reservation reservation = new Reservation();
        // User user = userService.getUserById(id);
        // reservation.setUser(user);  
        model.addAttribute("reserve", reservation);
        return "reservation"; 
    }

    @GetMapping("/reserve/hotels")
    public String getHotels(@RequestParam("city") String city, 
                            @RequestParam("checkIn") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkIn,
                            @RequestParam("checkOut") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkOut,
                            @ModelAttribute("reservation") Reservation reservation, 
                            Model model) {

        reservation.setCity(city);
        reservation.setCheckIn(checkIn);
        reservation.setCheckOut(checkOut);
        
        model.addAttribute("hotels", hotelService.getHotelByCity(city));
        model.addAttribute("city", city);
        
        return "listing";   
    }

    @GetMapping("/reserve/hotels/room")
    public String getHotelRooms(@RequestParam("hotelId") Integer hotelId, 
                                @ModelAttribute("reservation") Reservation reservation, 
                                Model model) {
        if(hotelId != null) {
            Hotel hotel = hotelService.getHotelById(hotelId);
            List<Room> allRooms = roomService.getRoomsByHotelId(hotelId);

            LocalDate checkIn = reservation.getCheckIn();
            LocalDate checkOut = reservation.getCheckOut();
            
            // Map<Room, Boolean>: true = available, false = booked
            Map<Room, Boolean> roomAvailabilityMap = new LinkedHashMap<>();
            for (Room room : allRooms) {
                boolean isAvailable = roomService.isRoomAvailableDuring(room.getRoomId(), checkIn, checkOut);
                roomAvailabilityMap.put(room, isAvailable);
            }
            model.addAttribute("hotel", hotel);
            model.addAttribute("roomAvailabilityMap", roomAvailabilityMap);
            
            
        }
        return "listing";
    }

    @GetMapping("/reserve/hotels/room/amenities")
    public String getRoomAmenities(@ModelAttribute("reservation") Reservation reservation, 
                                    @RequestParam("hotelId") Integer hotelId, 
                                    @RequestParam("roomId") Integer roomId, 
                                    Model model) {

        if (roomId != null && hotelId != null) {
            Hotel hotel = hotelService.getHotelById(hotelId);
            Room selectedRoom = roomService.getRoomById(roomId);
            List<Room> allRooms = roomService.getRoomsByHotelId(hotelId);

            Map<Room, Boolean> roomAvailabilityMap = new LinkedHashMap<>();
            
            Map<String, Integer> roomAvailableCountMap = new HashMap<>(); 

            for (Room room : allRooms) {
                boolean isAvailable = roomService.isRoomAvailableDuring(
                    room.getRoomId(), reservation.getCheckIn(), reservation.getCheckOut());

                roomAvailabilityMap.put(room, isAvailable);

                if (isAvailable) {
                    String type = room.getRoomType();
                    roomAvailableCountMap.put(type, roomAvailableCountMap.getOrDefault(type, 0) + 1);
                }
            }
            
            int availableRooms = reservationService.getAvailableRooms(roomId, reservation.getCheckIn(), reservation.getCheckOut());

            model.addAttribute("availableRooms", availableRooms);
            model.addAttribute("roomAvailabilityMap", roomAvailabilityMap);
            model.addAttribute("roomAvailableCountMap", roomAvailableCountMap);
            model.addAttribute("hotel", hotel);
            model.addAttribute("selectedRoom", selectedRoom);
            model.addAttribute("amenities", amenitiesService.getAmenitiesByRoomId(roomId));
        }
        return "listing";
    }

    @GetMapping("/reserve/form")
    public String submitReservation(@RequestParam("roomId") Integer roomId,
                                    @RequestParam("hotelId") Integer hotelId,
                                    @RequestParam("roomQuantity") Integer roomQuantity,
                                    @ModelAttribute("reservation") Reservation reservation,
                                    Model model ) {

        Room room = roomService.getRoomById(roomId);
        Hotel hotel = hotelService.getHotelById(hotelId);
        
        reservation.setHotel(hotel);
        reservation.setRoom(room);
        reservation.setRoomsBooked(roomQuantity);
        reservation.setTotal(room.getPrice() * roomQuantity);                                        

        model.addAttribute("roomQuantity", roomQuantity);
        model.addAttribute("hotel", hotel);
        model.addAttribute("room", room);
        model.addAttribute("reserve", reservation);
        model.addAttribute("user", new User());

        return "reservation";
    }

    @PostMapping("/reservation")
    public String submitReservation(@ModelAttribute("reservation") Reservation reservation,
                                    @ModelAttribute User user,
                                    SessionStatus status,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {  
        user.setRole("user");                                
        userService.saveUser(user);
        reservation.setUser(user);
        reservationService.saveReservation(reservation);
        status.setComplete();
        redirectAttributes.addFlashAttribute("success", "Reservation done!");

        return "redirect:/confirmation";  
    }
}
