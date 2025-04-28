package com.user.service.services.impl;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.external.services.HotelService;
import com.user.service.repository.UserRepository;
import com.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User saveUser(User user) {
        //generate unique user id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /*
    @Override
    public User getUser(String userId) {
        //get user from database with help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !!" + userId));

        //fetch rating of above user from rating service
        //http://localhost:8083/api/rating/users/5a520c2c-eb94-47f5-9fbf-42aa22ecba63
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/api/rating/users/" + user.getUserId(), Rating[].class);
        logger.info("{}", ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel
            // http://localhost:8082/api/hotels/a50be636-4854-4548-b62e-5c3dbd93bc2a
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/api/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("response status code: {}", forEntity.getStatusCode());
            // set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

     */

    //Using Feign Client
    @Override
    public User getUser(String userId) {
        //get user from database with help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !!" + userId));

        //fetch rating of above user from rating service
        //http://localhost:8083/api/rating/users/5a520c2c-eb94-47f5-9fbf-42aa22ecba63
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/api/rating/users/" + user.getUserId(), Rating[].class);
        logger.info("{}", ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel
            // http://localhost:8082/api/hotels/a50be636-4854-4548-b62e-5c3dbd93bc2a
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/api/hotels/" + rating.getHotelId(), Hotel.class);
            //Hotel hotel = forEntity.getBody();

            //using feign client
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            //logger.info("response status code: {}", forEntity.getStatusCode());
            // set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }
}
