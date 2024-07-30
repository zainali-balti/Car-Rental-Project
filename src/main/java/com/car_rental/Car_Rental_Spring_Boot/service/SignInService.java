package com.car_rental.Car_Rental_Spring_Boot.service;
import com.car_rental.Car_Rental_Spring_Boot.dao.SignInDao;
import com.car_rental.Car_Rental_Spring_Boot.domain.SignIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class SignInService {
    @Autowired
    private SignInDao signInDao;
    public ResponseEntity<List<SignIn>> getAllSignInUser() {
        log.info("Getting List of User...");
        try {
            log.info("Successfully getting all user..");
            return new ResponseEntity<>(signInDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addUser(SignIn signIn) {
        log.info("Added a new User...");
        try{
            signInDao.save(signIn);
            log.info("Successfully added a new User.");
            return new ResponseEntity<>("yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateUser(SignIn signIn) {
        log.info("Updated a user Record...");
        SignIn updateUser = signInDao.findById(signIn.getId()).get();
        if (updateUser.getId() == null)
            return ResponseEntity.ok("Not found"+signIn.getId());
        updateUser.setUserName(signIn.getUserName());
        updateUser.setPass(signIn.getPass());
        updateUser.setConPass(signIn.getConPass());
        try {
            signInDao.save(updateUser);
            log.info("Successfully Updated a user Record.");
            return new ResponseEntity<>("yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteUser(Integer id) {
        log.info("Deleting a user Record...");
        try {
            signInDao.deleteById(id);
            log.info("Successfully Deleted a user Record");
            return new ResponseEntity<>("yes",HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>("no",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<SignIn>> findByUserName(String userName) {
        log.info("Getting All user's Name....");
        try {
            log.info("Successfully getting all user Name.");
            return new ResponseEntity<>(signInDao.findByUserName(userName),HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
}
