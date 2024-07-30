package com.car_rental.Car_Rental_Spring_Boot.dao;

import com.car_rental.Car_Rental_Spring_Boot.domain.Vehicle_owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Vehicle_OwnerDao extends JpaRepository<Vehicle_owner,Integer> {
    List<Vehicle_owner> findByOwnerName(String ownerName);
}
