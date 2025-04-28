package com.user.service.external.services;

import com.user.service.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {

    @GetMapping("/api/hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId);

}
