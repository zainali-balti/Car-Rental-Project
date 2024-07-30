package com.car_rental.Car_Rental_Spring_Boot.controller;

import com.car_rental.Car_Rental_Spring_Boot.domain.Booking;
import com.car_rental.Car_Rental_Spring_Boot.dto.BookingDTO;
import com.car_rental.Car_Rental_Spring_Boot.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Operation(summary = "Get all Booking ")
    @GetMapping("get")
    public ResponseEntity<List<BookingDTO>> getAllBooking(){

        return bookingService.getAllBooking();
    }
    @Operation(summary = "Delete a specific Booking Data")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteFromBooking(@PathVariable Integer id){
        return bookingService.deleteFromBooking(id);
    }
    @Operation(summary = "Add into Booking Table")
    @PostMapping("add")
    public ResponseEntity<String> insertIntoBooking(@RequestBody BookingDTO booking){
        return bookingService.addIntoBooking(booking);
    }
    @Operation(summary = "Update a Specific Record into Booking Table")
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable Integer id, @RequestBody Booking booking){
         booking.setId(id);
        return bookingService.updateBooking(booking);
    }
}
