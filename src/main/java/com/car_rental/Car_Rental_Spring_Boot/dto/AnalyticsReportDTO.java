package com.car_rental.Car_Rental_Spring_Boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalyticsReportDTO {
    private Integer mId;
    private String vehicleNameMostRentOut;
    private String ownerNameMostRentOut;
    private Double mostCommission;
    private Integer lId;
    private String vehicleNameLeastRentOut;
    private String ownerNameLeastRentOut;
    private Double leastCommission;

    public static List<AnalyticsReportDTO> mappedTo(List<Object[]> data) {
        return data.stream().map(e -> {
            AnalyticsReportDTO report = new AnalyticsReportDTO();
            report.setMId(Integer.parseInt("" + e[0]));
            report.setVehicleNameMostRentOut((String) e[1]);
            report.setOwnerNameMostRentOut((String) e[2]);
            report.setMostCommission(Double.parseDouble("" + e[3]));
            report.setLId(Integer.parseInt("" + e[4]));
            report.setVehicleNameLeastRentOut((String) e[5]);
            report.setOwnerNameLeastRentOut((String) e[6]);
            report.setLeastCommission(Double.parseDouble("" + e[7]));
            return report;
            }).toList();
    }

}
