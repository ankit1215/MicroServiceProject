package com.rating.controller;

import com.rating.entity.Rating;
import com.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //create rating
    @PostMapping("/create")
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    //get ALL
    @GetMapping("/getAllRating")
    public ResponseEntity<List<Rating>> getAllRating(){
        return ResponseEntity.ok(ratingService.getAllRating());
    }

    //get All rating by user Id
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    //get All rating by hotel id
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

    //update rating
    @PutMapping("/update/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, @RequestBody Rating rating){
        Rating updateRating = ratingService.updateRating(ratingId, rating);
        return ResponseEntity.ok(updateRating);
    }

    //delete Rating
    @DeleteMapping("delete/{ratingId}")
    public ResponseEntity<String> deleteRating(@PathVariable String ratingId){
        ratingService.deleteRating(ratingId);
        return ResponseEntity.ok("Rating Deleted Successfully with ID: " + ratingId);
    }



}
