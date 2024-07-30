package com.car_rental.Car_Rental_Spring_Boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarAvailableDTO {
    private Integer id;
    private String vehicleName;
    private String ownerName;
    private Integer model;
    private String brand;
    private String color;
}
