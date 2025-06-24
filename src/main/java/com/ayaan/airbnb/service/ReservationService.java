package com.ayaan.airbnb.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ayaan.airbnb.model.Reservation;
import com.ayaan.airbnb.model.Room;
import com.ayaan.airbnb.repository.ReservationRepository;

@Service
public class ReservationService {
    
    private final ReservationRepository reservationRepository;
    private final RoomService roomService;

    public ReservationService(ReservationRepository reservationRepository, RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.roomService = roomService;
    }

    public Reservation findByUserId(Integer userId) {
        return reservationRepository.findTopByUser_UserIdOrderByCheckInDesc(userId);
    }

    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }   

    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public int getAvailableRooms(Integer roomId, LocalDate checkIn, LocalDate checkOut) {
        int alreadyBooked = reservationRepository.totalRoomsBookedBetweenDates(roomId, checkIn, checkOut);
        
        Room room = roomService.getRoomById(roomId);
        int totalQuantity = room.getRoomQuantity();

        return Math.max(totalQuantity - alreadyBooked, 0);
    }

}
