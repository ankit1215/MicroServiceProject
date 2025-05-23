package com.user.service;

import com.user.service.entity.Rating;
import com.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createRating(){
		Rating rating = Rating.builder().ratings(10).userId("").hotelId("").feedback("This is created using feign client").build();
		Rating savedRating = ratingService.createRating(rating);
		System.out.println("New Rating Created...");
	}

}
