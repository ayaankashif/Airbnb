package com.ayaan.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayaan.airbnb.model.Amenities;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Integer> {
    // Custom query methods can be defined here if needed

    
}
