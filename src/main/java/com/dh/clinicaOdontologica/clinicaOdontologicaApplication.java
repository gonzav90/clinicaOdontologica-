package com.dh.clinicaOdontologica;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class clinicaOdontologicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(clinicaOdontologicaApplication.class, args);
		PropertyConfigurator.configure("log4j.properties");

	}
}
