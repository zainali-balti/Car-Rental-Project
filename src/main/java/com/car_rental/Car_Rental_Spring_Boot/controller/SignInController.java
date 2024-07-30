package com.car_rental.Car_Rental_Spring_Boot.controller;
import com.car_rental.Car_Rental_Spring_Boot.domain.SignIn;
import com.car_rental.Car_Rental_Spring_Boot.service.SignInService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("signIn")
public class SignInController {
    @Autowired
    private SignInService signInService;
    @Operation(summary = "Get all User")
    @GetMapping("get")
    public ResponseEntity<List<SignIn>> getAllSigInUser(){

        return signInService.getAllSignInUser();
    }
    @Operation(summary = "Add new User")
    @PostMapping("add")
    public ResponseEntity<String> addUser(@RequestBody SignIn signIn){
        return signInService.addUser(signIn);
    }
    @Operation(summary = "Update Specific User")
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id,@RequestBody SignIn signIn){
        signIn.setId(id);
        return signInService.updateUser(signIn);
    }
    @Operation(summary = "Delete Specific User")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id){
        return signInService.deleteUser(id);
    }
    @Operation(summary = "Get all user By Name")
    @GetMapping("get/{userName}")
    public ResponseEntity<List<SignIn>> findByName(@PathVariable String userName){
        return signInService.findByUserName(userName);
    }
}
