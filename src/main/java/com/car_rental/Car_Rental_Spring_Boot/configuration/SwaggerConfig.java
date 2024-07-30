package com.car_rental.Car_Rental_Spring_Boot.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI registrationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("CAR RENTAL PROJECT")
                        .description("Its my first project on spring boot it " +
                                "is challenging " +
                                "for me to write an code in java Spring boot.")
                        .version("1.0"));
    }
}


