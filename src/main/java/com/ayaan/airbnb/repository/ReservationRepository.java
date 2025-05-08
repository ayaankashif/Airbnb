package com.ayaan.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayaan.airbnb.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // Custom query methods can be defined here if needed   
    
}
