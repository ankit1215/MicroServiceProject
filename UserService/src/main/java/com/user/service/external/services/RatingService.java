package com.user.service.external.services;

import com.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    //create
    @PostMapping("/api/rating/create")
    public Rating createRating(Rating values);

    //put
    @PutMapping("/api/rating/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

    //delete
    @DeleteMapping("/api/rating/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") String ratingId);


}
