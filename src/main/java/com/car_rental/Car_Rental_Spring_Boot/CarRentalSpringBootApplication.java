package com.car_rental.Car_Rental_Spring_Boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.car_rental.Car_Rental_Spring_Boot.dao")
@EntityScan("com.car_rental.Car_Rental_Spring_Boot.domain")
@ComponentScan("com.car_rental.Car_Rental_Spring_Boot")
public class CarRentalSpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(CarRentalSpringBootApplication.class, args);

	}
}
