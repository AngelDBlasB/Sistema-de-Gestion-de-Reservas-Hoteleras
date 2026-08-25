package com.blasalda.habitaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.blasalda.habitaciones", "com.blasalda.commons"})
public class HabitacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabitacionesApplication.class, args);
	}

}
