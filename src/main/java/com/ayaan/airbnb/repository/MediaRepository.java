package com.ayaan.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayaan.airbnb.model.Media;

public interface MediaRepository extends JpaRepository<Media, Integer> {
    // Custom query methods can be defined here if needed
    
}
