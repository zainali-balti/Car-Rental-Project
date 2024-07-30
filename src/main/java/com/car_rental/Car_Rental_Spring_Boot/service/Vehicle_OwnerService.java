package com.car_rental.Car_Rental_Spring_Boot.service;

import com.car_rental.Car_Rental_Spring_Boot.dao.Vehicle_OwnerDao;
import com.car_rental.Car_Rental_Spring_Boot.domain.Vehicle_owner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class Vehicle_OwnerService {
    @Autowired
    private Vehicle_OwnerDao vehicleOwnerDao;
    public ResponseEntity<List<Vehicle_owner>> getAllOwner() {
        log.info("Getting List of Vehicle Owner...");
        try {
            log.info("Successfully getting List of owner..");
            return new ResponseEntity<>(vehicleOwnerDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addIntoVehicleOwner(Vehicle_owner vehicleOwner) {
        log.info("Added a Vehicle Owwner ......");
        try {
            vehicleOwnerDao.save(vehicleOwner);
            log.info("Successfully added a new owner.");
            return new ResponseEntity<>("yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateVehicleOwner(Vehicle_owner vehicleOwner) {
        log.info("Update a Vehicle Owner Record...");
        Vehicle_owner updateVehicleOwner = vehicleOwnerDao.findById(vehicleOwner.getId()).get();
        updateVehicleOwner.setOwnerName(vehicleOwner.getOwnerName());
        updateVehicleOwner.setCnic(vehicleOwner.getCnic());
        updateVehicleOwner.setAddress(vehicleOwner.getAddress());
        updateVehicleOwner.setCommission(vehicleOwner.getCommission());
        updateVehicleOwner.setPhone_number(vehicleOwner.getPhone_number());
        try{
            vehicleOwnerDao.save(updateVehicleOwner);
            log.info("Successfully Updated  owner..");
            return new ResponseEntity<>("yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteOwner(Integer id) {
        log.info("Deleting a Vehicle Owner....");
    try {
            vehicleOwnerDao.deleteById(id);
            log.info("Successfully Deleted a Vehicle Owner.");
            return new ResponseEntity<>("yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Vehicle_owner>> getByName(String ownerName) {
        log.info("Getting Vehicle Owner By Name....");
        try {
            log.info("Successfully Getting Owner Name.");
            return new ResponseEntity<>(vehicleOwnerDao.findByOwnerName(ownerName),HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
}
