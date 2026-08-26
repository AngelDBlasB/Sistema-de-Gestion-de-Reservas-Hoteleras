package com.blasalda.reservaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.blasalda.reservaciones", "com.blasalda.commons"})
public class ReservacionesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservacionesApplication.class, args);
    }

}
