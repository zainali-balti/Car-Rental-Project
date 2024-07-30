package com.car_rental.Car_Rental_Spring_Boot.service;

import com.car_rental.Car_Rental_Spring_Boot.dao.VehicleDao;
import com.car_rental.Car_Rental_Spring_Boot.dao.Vehicle_OwnerDao;
import com.car_rental.Car_Rental_Spring_Boot.domain.Vehicle;
import com.car_rental.Car_Rental_Spring_Boot.domain.Vehicle_owner;
import com.car_rental.Car_Rental_Spring_Boot.dto.VehicleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private Vehicle_OwnerDao vehicleOwnerDao;

    public ResponseEntity<List<VehicleDTO>> getAllVehicle() {
        log.info("Getting List Of Vehicle...");
        try {
            List<Vehicle> vehicles = vehicleDao.findAll();
            List<VehicleDTO> vehicleDTOS = VehicleDTO.mapped(vehicles);
            log.info("Successfully Getting all the List of Vehicle.");
            return new ResponseEntity<>(vehicleDTOS,HttpStatus.OK) ;
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Vehicle>> getAllByName(String vehicleName) {
        log.info("Getting All the Vehicle By Name...");
        try{
            List<Vehicle> vehiclesName = vehicleDao.findByVehicleName(vehicleName);
//            List<VehicleDTO> vehicleDTOS = VehicleDTO.mapped(vehiclesName);
            log.info("Successfully Getting All the Vehicle By Name.");
            return new ResponseEntity<>(vehiclesName,HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addVehicle(VehicleDTO vehicle) {
        log.info("Added a new Vehicle....");
        try {
            Optional<Vehicle_owner> vehicleOwner = vehicleOwnerDao.findById(vehicle.getVehicleOwnerId());
            if(vehicleOwner.isEmpty())
                return new ResponseEntity<>("No vehicle owner found.",HttpStatus.BAD_REQUEST);
            Vehicle vehicle1 = new Vehicle();
            vehicle1.setColor(vehicle.getColor());
            vehicle1.setVehicleOwner(vehicleOwner.get());
            vehicle1.setModel(vehicle.getModel());
            vehicle1.setBrand(vehicle.getBrand());
            vehicle1.setVehicleName(vehicle.getVehicleName());

            vehicleDao.save(vehicle1);
            log.info("Successfully Added a new Vehicle...");
            return new ResponseEntity<>("yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateVehicle(Vehicle vehicle) {
        log.info("Updating vehicle......");
        Optional<Vehicle> checkingVehicle = vehicleDao.findById(vehicle.getId());
        if (!checkingVehicle.isPresent()) {
            log.error("Sorry updating Failed....");
            return new ResponseEntity<>("Vehicle not found",
                    HttpStatus.NOT_FOUND);
        }
        Vehicle updateVehicle = checkingVehicle.get();
        updateVehicle.setVehicleName(vehicle.getVehicleName());
        updateVehicle.setId(vehicle.getVehicleOwner().getId());
        updateVehicle.setColor(vehicle.getColor());
        updateVehicle.setBrand(vehicle.getBrand());
        updateVehicle.setModel(vehicle.getModel());
        try{
            vehicleDao.save(updateVehicle);
            log.info("Updating Successfully.....");
            return new ResponseEntity<>("yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteVehicle(Integer id) {
        log.info("Deleting a Vehicle....");
        try {
            vehicleDao.deleteById(id);
            log.info("Deleted a Vehicle Successfully.");
            return new ResponseEntity<>("yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }
}
