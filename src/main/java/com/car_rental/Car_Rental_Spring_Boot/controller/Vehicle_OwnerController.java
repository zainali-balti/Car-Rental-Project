package com.car_rental.Car_Rental_Spring_Boot.controller;

import com.car_rental.Car_Rental_Spring_Boot.domain.Vehicle_owner;
import com.car_rental.Car_Rental_Spring_Boot.service.Vehicle_OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicleOwner")
public class Vehicle_OwnerController {
    @Autowired
    private Vehicle_OwnerService vehicleOwnerService;

    @Operation(summary = "Get all Vehicle Owner ")
    @GetMapping("get")
    public ResponseEntity<List<Vehicle_owner>> getAllOwner() {
        return vehicleOwnerService.getAllOwner();
    }

    @Operation(summary = "Add new Vehicle Owner")
    @PostMapping("add")
    public ResponseEntity<String> addVehicleOwner(@RequestBody Vehicle_owner vehicleOwner) {
        return vehicleOwnerService.addIntoVehicleOwner(vehicleOwner);
    }

    @Operation(summary = "Update Specific Owner")
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateVehicleOwner(@PathVariable Integer id,@RequestBody Vehicle_owner vehicleOwner) {
        vehicleOwner.setId(id);
        return vehicleOwnerService.updateVehicleOwner(vehicleOwner);
    }
    @Operation(summary = "Delete Specific Owner")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Integer id){
        return vehicleOwnerService.deleteOwner(id);
    }
    @Operation(summary = "Get all Owner By Name")
    @GetMapping("get/{ownerName}")
    public ResponseEntity<List<Vehicle_owner>> getByName(@PathVariable String ownerName){
        return vehicleOwnerService.getByName(ownerName);
    }

}

