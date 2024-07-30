package com.car_rental.Car_Rental_Spring_Boot.service;

import com.car_rental.Car_Rental_Spring_Boot.dao.BookingDao;
import com.car_rental.Car_Rental_Spring_Boot.dto.AnalyticsReportDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class AnalyticsReportService {
    @Autowired
    private BookingDao bookingDao;
    public ResponseEntity<List<AnalyticsReportDTO>> getAll() {
        log.info("Getting List of Analysis Report....");
        try{
            List<AnalyticsReportDTO> analyticsReportDTOList =
                    AnalyticsReportDTO.mappedTo(
                            bookingDao.findAllLeastAndMostCarRentOut());
            log.info("Completed Analysis Report....");
            return new ResponseEntity<>(analyticsReportDTOList, HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry so error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
}
