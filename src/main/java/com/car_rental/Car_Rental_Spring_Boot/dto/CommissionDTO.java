package com.car_rental.Car_Rental_Spring_Boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommissionDTO {
    private Integer id;
    private Double commission;
    private String vehicleName;
    private String color;
    private String ownerName;
}
