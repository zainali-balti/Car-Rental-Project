package com.car_rental.Car_Rental_Spring_Boot.dao;

import com.car_rental.Car_Rental_Spring_Boot.domain.Vehicle;
import com.car_rental.Car_Rental_Spring_Boot.dto.CarAvailableDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle,Integer> {
    List<Vehicle> findByVehicleName(String vehicleName);
    @Query("SELECT new com.car_rental.Car_Rental_Spring_Boot.dto.CarAvailableDTO" +
            "(v.id, v.vehicleName, v.vehicleOwner.ownerName, v.model, v.brand, v.color) " +
            "FROM Vehicle v LEFT JOIN Booking b ON v.id = b.vehicle.id WHERE" +
            " b.id IS NULL")
    List<CarAvailableDTO> findVehiclesNotBooked();
}
