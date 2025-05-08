package com.ayaan.airbnb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayaan.airbnb.model.Review;
import com.ayaan.airbnb.repository.ReviewRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }

    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review getReviewById(Integer id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
