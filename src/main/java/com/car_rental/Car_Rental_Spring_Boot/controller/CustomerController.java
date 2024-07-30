package com.car_rental.Car_Rental_Spring_Boot.controller;


import com.car_rental.Car_Rental_Spring_Boot.domain.Customer;
import com.car_rental.Car_Rental_Spring_Boot.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Operation(summary = "Add into Customer Table")
    @PostMapping("add")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }
    @Operation(summary = "Get all the Records of Customer")
    @GetMapping("get")
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customerService.getCustomers());
    }
    @Operation(summary = "Get Customer By Name")
    @GetMapping("get/{name}")
    public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable String name){
        return customerService.getByC_name(name);
    }
    @Operation(summary = "Update Specific Customer into Table")
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Integer id,
                                                 @RequestBody Customer customer){
       customer.setId(id);
        return customerService.updateCustomer(customer);
    }
    @Operation(summary = "Delete Specific Record From Customer")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteFromCustomer(@PathVariable Integer id){
        return customerService.deleteFromCustomer(id);
    }
}
