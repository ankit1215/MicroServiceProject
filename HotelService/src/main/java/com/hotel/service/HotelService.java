package com.hotel.service;

import com.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {

    //create a hotel
    Hotel create(Hotel hotel);

    //get All hotels
    List<Hotel> getAllHotels();

    //get single hotels
    Hotel getHotel(String id);
}
