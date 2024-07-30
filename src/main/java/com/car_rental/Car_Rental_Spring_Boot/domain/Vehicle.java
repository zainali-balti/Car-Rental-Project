package com.car_rental.Car_Rental_Spring_Boot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String vehicleName;
    private Integer model;
    private String brand;
    private String color;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Vehicle_owner vehicleOwner;
    @JsonIgnore
    @OneToMany(mappedBy = "vehicle",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Booking> bookings;

}
