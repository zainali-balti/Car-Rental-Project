package com.car_rental.Car_Rental_Spring_Boot.dao;


import com.car_rental.Car_Rental_Spring_Boot.domain.Booking;
import com.car_rental.Car_Rental_Spring_Boot.dto.CommissionDTO;
import com.car_rental.Car_Rental_Spring_Boot.dto.ReportDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingDao extends JpaRepository<Booking,Integer> {
@Query("SELECT new com.car_rental.Car_Rental_Spring_Boot.dto.ReportDTO(b.id," +
        " b.customer.id,b.customer.customerName, b.vehicle.id, b.vehicle.vehicleName, b.booking_date, b.price, b.bookingStatus) " +
        "FROM Booking b WHERE b.booking_date BETWEEN :startDate AND :endDate")
    List<ReportDTO> findBookingsBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
    @Query(value =
            "SELECT new com.car_rental.Car_Rental_Spring_Boot.dto.CommissionDTO(" +
            "    v.id, " +
            "    (b.price * 0.10) as commission, " +
            "    v.vehicleName, " +
            "    v.color, " +
            "    vo.ownerName) " +
            "FROM Booking b " +
            "JOIN Vehicle v ON b.vehicle.id = v.id " +
            "JOIN Vehicle_owner vo ON v.vehicleOwner.id = vo.id " +
            "WHERE b.booking_date BETWEEN :startDate AND :endDate")
    List<CommissionDTO> findCommissionBetweenDates(@Param("startDate") LocalDate startDate
        , @Param("endDate") LocalDate endDate);
    @Query(value = """
             SELECT
                                                               most_rented_out.id as mId,
                                                               most_rented_out.vehicle_name as vehicleNameMostRent,
                                                               most_rented_out.owner_name as ownerNameMostRent,
                                                               most_rented_out.commission as MostCommission,
                                                               least_rented_out.id as lId,
                                                               least_rented_out.vehicle_name as vehicleNameLeastRent,
                                                               least_rented_out.owner_name as ownerNameLeastRent,
                                                               least_rented_out.commission as LeastCommission
                                                               FROM
                                                               (SELECT
                                                               v.id,
                                                               v.vehicle_name,
                                                               vo.owner_name,
                                                               sum(vo.commission) as commission,
                                                               COUNT(b.id) AS total_bookings
                                                               FROM
                                                               vehicle v
                                                               JOIN
                                                               vehicle_owner vo ON v.id = vo.id
                                                               JOIN
                                                               booking b ON v.id = b.v_id
                                                               GROUP BY
                                                               v.id, v.vehicle_name, vo.owner_name, vo.commission
                                                               ORDER BY
                                                               total_bookings DESC
                                                               LIMIT 1) AS most_rented_out
                                                               JOIN
                                                               (SELECT
                                                               v.id,
                                                               v.vehicle_name,
                                                               vo.owner_name,
                                                               sum(vo.commission) as commission,
                                                               COUNT(b.id) AS total_bookings
                                                               FROM
                                                               vehicle v
                                                               JOIN
                                                               vehicle_owner vo ON v.id = vo.id
                                                               LEFT JOIN
                                                               booking b ON v.id = b.v_id
                                                                GROUP BY
                                                                 v.id, v.vehicle_name, vo.owner_name, vo.commission
                                                               ORDER BY
                                                               total_bookings ASC
                                                               LIMIT 1) AS least_rented_out ON 1=1
            """, nativeQuery = true)
    List<Object[]> findAllLeastAndMostCarRentOut();

}
