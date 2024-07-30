package com.car_rental.Car_Rental_Spring_Boot.controller;

import com.car_rental.Car_Rental_Spring_Boot.domain.Vehicle;
import com.car_rental.Car_Rental_Spring_Boot.dto.VehicleDTO;
import com.car_rental.Car_Rental_Spring_Boot.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Operation(summary = "Get all Vehicles")
    @GetMapping("get")
    public ResponseEntity<List<VehicleDTO>> getAllVehicle(){

        return vehicleService.getAllVehicle();
    }
    @Operation(summary = "Get all Vehicles By Name")
    @GetMapping("get/{vehicleName}")
    public ResponseEntity<List<Vehicle>> getAllByName(@PathVariable String vehicleName){
        return vehicleService.getAllByName(vehicleName);
    }
    @Operation(summary = "Add new vehicle")
    @PostMapping("add")
    public ResponseEntity<String> addVehicle(@RequestBody VehicleDTO vehicle){
        return vehicleService.addVehicle(vehicle);
    }
    @Operation(summary = "Update a Specific Vehicle Detail")
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable Integer id,@RequestBody Vehicle vehicle){
        vehicle.setId(id);
        return vehicleService.updateVehicle(vehicle);
    }
    @Operation(summary = "Delete a Specific Vehicle Detail")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Integer id){
        return vehicleService.deleteVehicle(id);
    }
}
