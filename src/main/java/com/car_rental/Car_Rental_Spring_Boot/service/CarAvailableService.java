package com.car_rental.Car_Rental_Spring_Boot.service;

import com.car_rental.Car_Rental_Spring_Boot.dao.VehicleDao;
import com.car_rental.Car_Rental_Spring_Boot.dto.CarAvailableDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CarAvailableService {
    @Autowired
    private VehicleDao vehicleDao;
        public ResponseEntity<List<CarAvailableDTO>> getAll() {
            log.info("Getting List of Car Available....");
        try {
            List<CarAvailableDTO> carAvailableDTOList =
                    vehicleDao.findVehiclesNotBooked();
            log.info("Completed List of Car Available....");
            return new ResponseEntity<>(carAvailableDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Sorry error is generated.....");
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    }

