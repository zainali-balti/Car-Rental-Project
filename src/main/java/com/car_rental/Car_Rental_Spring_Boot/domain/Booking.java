package com.car_rental.Car_Rental_Spring_Boot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "c_id")
    private Customer customer;
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "v_id")
    private Vehicle vehicle;
    private LocalDate booking_date;
    private Integer price;
    private String bookingStatus;
}
