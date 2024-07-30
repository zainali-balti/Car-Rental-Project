package com.car_rental.Car_Rental_Spring_Boot.dao;

import com.car_rental.Car_Rental_Spring_Boot.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Integer> {
         List<Customer> findByCustomerName(String cName);

}
