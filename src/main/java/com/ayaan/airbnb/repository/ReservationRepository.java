package com.ayaan.airbnb.repository;


import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ayaan.airbnb.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findTopByUser_UserIdOrderByCheckInDesc(Integer userId);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.room.id = :roomId AND NOT (r.checkOut <= :checkIn OR r.checkIn >= :checkOut)")
    long countOverlappingReservations(@Param("roomId") Integer roomId,
                                    @Param("checkIn") LocalDate checkIn,
                                    @Param("checkOut") LocalDate checkOut);
}
