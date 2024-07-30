package com.car_rental.Car_Rental_Spring_Boot.controller;

import com.car_rental.Car_Rental_Spring_Boot.domain.User;
import com.car_rental.Car_Rental_Spring_Boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Operation(summary = "User Authentication ")
    @PostMapping("login")
    public ResponseEntity<String> CheckUserAuthentication(@RequestBody User user){
        boolean isAuthenticated = userService.CheckUserAuthentication(user);
        if (isAuthenticated) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Sorry Not Found",HttpStatus.BAD_REQUEST);
        }
    }
}
