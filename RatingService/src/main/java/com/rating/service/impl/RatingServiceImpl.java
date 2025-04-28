package com.rating.service.impl;

import com.rating.entity.Rating;
import com.rating.repository.RatingRepository;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    //create rating
    @Override
    public Rating create(Rating rating) {
        String randomRatingId = UUID.randomUUID().toString();
        rating.setRatingId(randomRatingId);
        return ratingRepository.save(rating);
    }


    //get allRating
    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    //getgetRatingByUserId
    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    //getRatingByHotelId
    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    //update rating
    @Override
    public Rating updateRating(String ratingId, Rating rating) {
       Rating existingRating = ratingRepository.findById(ratingId).orElseThrow(()-> new RuntimeException("Rating not found with id"));
       existingRating.setUserId(rating.getUserId());
       existingRating.setHotelId(rating.getHotelId());
       existingRating.setRatings(rating.getRatings());
       existingRating.setFeedback(rating.getFeedback());
       return ratingRepository.save(existingRating);
    }

    //delete rating
    @Override
    public void deleteRating(String ratingId) {
        ratingRepository.deleteById(ratingId);

    }
}
