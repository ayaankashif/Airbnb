package com.ayaan.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayaan.airbnb.model.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {
    // Custom query methods can be defined here if needed
    
}
