package com.ayaan.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayaan.airbnb.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // Custom query methods can be defined here if needed
    
}
