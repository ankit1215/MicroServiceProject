package com.user.service.services.impl;

import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.repository.UserRepository;
import com.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    public User getUser(String userId) {
        //get user from database with help of user repository
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server !!" + userId));

        //fetch rating of above user from rating service
        //http://localhost:8083/api/rating/users/5a520c2c-eb94-47f5-9fbf-42aa22ecba63
        ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8083/api/rating/users/" + user.getUserId(), ArrayList.class);
        logger.info("{}",ratingOfUser);
        user.setRatings(ratingOfUser);
        return user;
    }
}
