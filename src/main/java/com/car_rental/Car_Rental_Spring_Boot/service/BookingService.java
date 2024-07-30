package com.car_rental.Car_Rental_Spring_Boot.service;

import com.car_rental.Car_Rental_Spring_Boot.dao.BookingDao;
import com.car_rental.Car_Rental_Spring_Boot.dao.CustomerDao;
import com.car_rental.Car_Rental_Spring_Boot.dao.VehicleDao;
import com.car_rental.Car_Rental_Spring_Boot.domain.Booking;
import com.car_rental.Car_Rental_Spring_Boot.domain.Customer;
import com.car_rental.Car_Rental_Spring_Boot.domain.Vehicle;
import com.car_rental.Car_Rental_Spring_Boot.dto.BookingDTO;
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
public class BookingService {
    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private VehicleDao vehicleDao;
    public ResponseEntity<List<BookingDTO>> getAllBooking() {
        log.info("Getting List of Booking...");
        try{
            List<Booking> bookings = bookingDao.findAll();
            List<BookingDTO> bookingDTOS = BookingDTO.mapped(bookings);
             log.info("Completed List of Booking.");
            return new ResponseEntity<>(bookingDTOS,HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated");
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteFromBooking(Integer id) {
        log.info("Delete a BookingRecord..");
        try {
            bookingDao.deleteById(id);
            log.info("Deleted a BookingRecord Successfully.");
            return new ResponseEntity<>("yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addIntoBooking(BookingDTO booking) {
        log.info("Add into Booking...");
        try{
            Optional<Customer> customer = customerDao.findById(booking.getCustomerId());
            Optional<Vehicle> vehicle = vehicleDao.findById(booking.getVehicleId());
            if (customer.isEmpty() || vehicle.isEmpty())
                return new ResponseEntity<>("customer and vehicle not found"
                        ,HttpStatus.BAD_REQUEST);
            Booking booking1 = new Booking();
            booking1.setCustomer(customer.get());
            booking1.setVehicle(vehicle.get());
            booking1.setBooking_date(booking.getBookingDate());
            booking1.setPrice(booking.getPrice());
            booking1.setBookingStatus(booking.getBookingStatus());
            bookingDao.save(booking1);
            log.info("Successully added a Record into Booking.");
            return new ResponseEntity<>("Yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> updateBooking(Booking updateBookings) {
        log.info("Updating booking...");
        try {
            Optional<Booking> bookingOptional = bookingDao.findById
                    (updateBookings.getId());

            if (!bookingOptional.isPresent()) {
                log.error("Sorry, Booking ID not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Sorry, Booking ID not found: "
                                + updateBookings.getId());
            }

            Booking updateBooking = bookingOptional.get();

            updateBooking.setCustomer(updateBookings.getCustomer());
            updateBooking.setVehicle(updateBookings.getVehicle());
            updateBooking.setBooking_date(updateBookings.getBooking_date());
            updateBooking.setPrice(updateBookings.getPrice());
            updateBooking.setBookingStatus(updateBookings.getBookingStatus());

            bookingDao.save(updateBooking);
            log.info("Booking is updated successfully.");
            return new ResponseEntity<>("Booking updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update booking", HttpStatus.BAD_REQUEST);
        }

    }


}
