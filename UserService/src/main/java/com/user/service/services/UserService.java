package com.user.service.services;

import com.user.service.entity.User;

import java.util.List;

public interface UserService {

    //create a user
    User saveUser(User user);

    //get All users
    List<User> getAllUsers();


    //get single user of given user id
    User getUser(String userId);

}
