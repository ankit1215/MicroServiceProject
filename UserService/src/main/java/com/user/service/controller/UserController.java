package com.user.service.controller;

import com.user.service.entity.User;
import com.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //create

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //get Single user id
    int retryCount = 1;
    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Retry Count: " + retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //create a fall back method for circuitebreaker


    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        //logger.info("fallback is executed because service is down: ", ex.getMessage());
        User user = User.builder()
                .email("Dummy@gmail.com")
                .name("Dummy Kumar")
                .about("This user is created down becasue service is down")
                .userId("12345")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);


    }

    //get All Users
    @GetMapping("getAllUsers")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }
}
