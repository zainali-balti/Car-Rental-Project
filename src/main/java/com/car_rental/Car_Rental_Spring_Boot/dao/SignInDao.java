package com.car_rental.Car_Rental_Spring_Boot.dao;
import com.car_rental.Car_Rental_Spring_Boot.domain.SignIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignInDao extends JpaRepository<SignIn,Integer> {
    List<SignIn> findByUserName(String userName);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END" +
            " FROM SignIn s WHERE s.userName = :userName AND s.pass = :pass")
    boolean existsByUserNameAndPass(@Param("userName") String
                                            userName, @Param("pass") String pass);
}
