package com.car_rental.Car_Rental_Spring_Boot.dto;


import com.car_rental.Car_Rental_Spring_Boot.domain.Booking;
import com.car_rental.Car_Rental_Spring_Boot.domain.Customer;
import com.car_rental.Car_Rental_Spring_Boot.domain.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Integer bookingId;
    private Integer customerId;
    private Integer vehicleId;
    private LocalDate bookingDate;
    private Integer price;
    private String bookingStatus;
    public  BookingDTO(Booking booking){
        this.bookingId = booking.getId();
        this.customerId = Optional.ofNullable(booking.getCustomer())
                .map(Customer::getId)
                .orElse(null);
        this.vehicleId = Optional.ofNullable(booking.getVehicle())
                .map(Vehicle::getId)
                .orElse(null);
        this.bookingDate = booking.getBooking_date();
        this.price = booking.getPrice();
        this.bookingStatus = booking.getBookingStatus();
    }


    public static List<BookingDTO> mapped(List<Booking> bookings) {
        return  bookings.stream()
                .map(BookingDTO::new) // Using the constructor to convert each Booking to BookingDTO
                .collect(Collectors.toList());
    }
}
