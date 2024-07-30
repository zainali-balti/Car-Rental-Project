package com.car_rental.Car_Rental_Spring_Boot.service;

import com.car_rental.Car_Rental_Spring_Boot.dao.CustomerDao;
import com.car_rental.Car_Rental_Spring_Boot.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    public ResponseEntity<String> addCustomer(Customer customer) {
        log.info("Added Customer in CustomerRecord...");
        try{
            customerDao.save(customer);
            log.info("Successfully Added Customer Record..");
            return new ResponseEntity<>("Yes Inserted Successfully",
                    HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Inserted Yet",
                HttpStatus.BAD_REQUEST);
    }

    @Transactional(readOnly=true)
    public List<Customer> getCustomers() {
        log.info("Getting List of Customer Record..");
        try{
            log.info("Successfully get Customer Record");
            return customerDao.findAll();
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return null;
    }

    public ResponseEntity<List<Customer>> getByC_name(String cName) {
        log.info("Getting List of Customer Name...");
        try{
            log.info("Successfully Getting List Of Customer Name..");
            return new ResponseEntity<>(customerDao.findByCustomerName(cName),
                    HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateCustomer(Customer customer) {
        log.info("Updating Customer Record....");
        Customer updatingCustomer = customerDao.findById(customer.getId()).get();
        updatingCustomer.setCustomerName(customer.getCustomerName());
        updatingCustomer.setCnic(customer.getCnic());
        updatingCustomer.setAddress(customer.getAddress());
        updatingCustomer.setPhone_number(customer.getPhone_number());
        if(updatingCustomer.getId() == null)
            return ResponseEntity
                    .ok("No customer found with provided customer id: "+ customer.getId());

        try{
            customerDao.save(updatingCustomer);
            log.info("Successfully updated a Customer Record.");
            return  new ResponseEntity<>("Yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteFromCustomer(Integer id) {
        log.info("Deleted a Customer Record...");
        try{
            customerDao.deleteById(id);
            log.info("Successfully Deleted a Customer Record.");
            return new ResponseEntity<>("Yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }
}
