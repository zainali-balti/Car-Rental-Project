package com.car_rental.Car_Rental_Spring_Boot.service;

import com.car_rental.Car_Rental_Spring_Boot.dao.BookingDao;
import com.car_rental.Car_Rental_Spring_Boot.dto.CommissionDTO;
import com.car_rental.Car_Rental_Spring_Boot.dto.TimeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class CommissionService {
    @Autowired
    private BookingDao bookingDao;
    public ResponseEntity<List<CommissionDTO>> getAll(TimeDTO timeDto) {
        log.info("Getting List of Commission....");
        try {
            List<CommissionDTO> commissionDTOS =
                    bookingDao.findCommissionBetweenDates
                            (timeDto.getStartDate(), timeDto.getStartDate());
            log.info("Completed List of Commission..");
            return new ResponseEntity<>(commissionDTOS, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Sorry error is generated");
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

}

