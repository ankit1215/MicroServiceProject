package com.hotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @Column(name = "HotelId")
    private String id;
    @Column(name = "HotelName")
    private String name;
    @Column(name = "HotelLocation")
    private String location;
    @Column(name = "HotelAbout")
    private String about;

}
