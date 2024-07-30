package com.car_rental.Car_Rental_Spring_Boot.service;

import com.car_rental.Car_Rental_Spring_Boot.dao.BookingDao;
import com.car_rental.Car_Rental_Spring_Boot.dto.ReportDTO;
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
public class ReportService {
    @Autowired
    private BookingDao bookingDao;

    public ResponseEntity<List<ReportDTO>> getAll(TimeDTO timeDto) {
        log.info("Getting List of Report...");
        try {
            List<ReportDTO> reportDTOList =
                    bookingDao.findBookingsBetweenDates
                            (timeDto.getStartDate(),timeDto.getEndDate());
            log.info("Completed List of Report.");
            return new ResponseEntity<>(reportDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Sorry error is generated");
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
