package com.ayaan.airbnb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayaan.airbnb.model.Reservation;
import com.ayaan.airbnb.repository.ReservationRepository;

@Service
public class ReservationService {
    
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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

}
