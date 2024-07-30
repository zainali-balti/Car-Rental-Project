package com.car_rental.Car_Rental_Spring_Boot.dto;

import com.car_rental.Car_Rental_Spring_Boot.domain.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private Integer vehicleId;
    private String vehicleName;
    private Integer model;
    private String brand;
    private String color;
    private Integer vehicleOwnerId;

    public  VehicleDTO(Vehicle vehicle){
        this.vehicleId = vehicle.getId();
        this.vehicleName = vehicle.getVehicleName();
        this.model = vehicle.getModel();
        this.brand = vehicle.getBrand();
        this.color = vehicle.getColor();
        if (vehicle.getVehicleOwner() != null) {
            this.vehicleOwnerId = vehicle.getVehicleOwner().getId();
        } else {
            this.vehicleOwnerId = null; // Or handle it in a way that makes sense for your application
        }
//        this.vehicleOwnerId = vehicle.getVehicleOwner().getId();
    }

    public static List<VehicleDTO> mapped(List<Vehicle> vehicles){
        return vehicles.stream().map(VehicleDTO::new).collect(Collectors.toList());
    }
}
