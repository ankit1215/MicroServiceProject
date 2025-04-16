package com.rating.service;

import com.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    //create a rating
    Rating create(Rating rating);

    //get All Rating
    List<Rating> getAllRating();

    //get all by user id
    List<Rating> getRatingByUserId(String userId);

    // get all by hotelId
    List<Rating> getRatingByHotelId(String hotelId);


}
