package com.car_rental.Car_Rental_Spring_Boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeDTO {
    private LocalDate startDate;
    private LocalDate endDate;
}
