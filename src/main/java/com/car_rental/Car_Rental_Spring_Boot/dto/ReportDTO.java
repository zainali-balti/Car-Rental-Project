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
public class ReportDTO {
    private Integer id;
    private Integer customerId;
    private String customerName;
    private Integer vehicleId;
    private String vehicleName;
    private LocalDate bookingDate;
    private Integer price;
    private String status;
}
