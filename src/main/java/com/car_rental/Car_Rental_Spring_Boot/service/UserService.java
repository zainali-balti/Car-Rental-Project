package com.car_rental.Car_Rental_Spring_Boot.service;

import com.car_rental.Car_Rental_Spring_Boot.dao.SignInDao;
import com.car_rental.Car_Rental_Spring_Boot.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserService {
    @Autowired
    private SignInDao signInDao;
    public boolean CheckUserAuthentication(User user) {
        try {
            log.info("Checking User Authentication...");
            return signInDao.existsByUserNameAndPass(user.getUserName(), user.getPass());
        }
        catch (Exception e){
            log.error("Sorry error is generated....");
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

}
