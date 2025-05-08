package com.ayaan.airbnb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayaan.airbnb.model.Media;
import com.ayaan.airbnb.repository.MediaRepository;

@Service
public class MediaService {
    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public void deleteMedia(Integer id) {
        mediaRepository.deleteById(id);
    }

    public Media saveMedia(Media media) {
        return mediaRepository.save(media);
    }   
    
    public Media getMediaById(Integer id) {
        return mediaRepository.findById(id).orElse(null);
    }
    
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }
    
    public Media updatateMedia(Media media) {
        return mediaRepository.save(media);
    }
    

    
    // public void deleteAllMediaByRoomId(Integer roomId) {
    //     mediaRepository.deleteAllByRoomId(roomId);
    // }
    // public void deleteAllMediaByUserId(Integer userId) {
        //     mediaRepository.deleteAllByUserId(userId);
    // }

    // public void deleteAllMediaByReviewId(Integer reviewId) {
    //     mediaRepository.deleteAllByReviewId(reviewId);
    // }

    // public void deleteAllMediaByUserIdAndRoomId(Integer userId, Integer roomId) {
    //     mediaRepository.deleteAllByUserIdAndRoomId(userId, roomId);
    // }
}
