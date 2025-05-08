package com.ayaan.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayaan.airbnb.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User login(String name); 
}
