package com.ayaan.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayaan.airbnb.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // Custom query methods can be defined here if needed
}
