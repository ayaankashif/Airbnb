package com.ayaan.airbnb.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "amenities")
public class Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amenities_id")
    private Integer amenitiesId;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @Column(name = "has_pool")
    private String hasPool;
    @Column(name = "has_bedroom")
    private String totalBedroom;
    @Column(name = "has_bathroom")
    private String totalBathroom;
    @Column(name = "has_aircon")
    private String hasAircon;
}
