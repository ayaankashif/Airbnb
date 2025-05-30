package com.ayaan.airbnb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayaan.airbnb.model.Amenities;
import com.ayaan.airbnb.repository.AmenitiesRepository;

@Service
public class AmenitiesService {
    private final AmenitiesRepository amenitiesRepository;

    public AmenitiesService(AmenitiesRepository amenitiesRepository) {
        this.amenitiesRepository = amenitiesRepository;
    }
    
    public List<Amenities> getAmenitiesByRoomId(Integer id){
        return amenitiesRepository.findByRoomRoomId(id);
    }

    public List<Amenities> getAllAmenities() {
        return amenitiesRepository.findAll();
    }
    
    public Amenities saveAmenities(Amenities amenities) {
        return amenitiesRepository.save(amenities);
    }
    
    public void deleteAmenities(Integer id) {
        amenitiesRepository.deleteById(id);
    }

    public Amenities updateAmenities(Amenities amenities) {
       return amenitiesRepository.save(amenities);
    }

    public Amenities getAmenitiesById(Integer id) {
        return amenitiesRepository.findById(id).orElse(null);
    }
}
