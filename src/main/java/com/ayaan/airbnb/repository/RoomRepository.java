package com.ayaan.airbnb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayaan.airbnb.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    // Custom query methods can be defined here if needed}}
}
