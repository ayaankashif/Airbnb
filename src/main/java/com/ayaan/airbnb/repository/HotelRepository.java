package com.ayaan.airbnb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayaan.airbnb.model.Hotel;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByCity(String city);
}
