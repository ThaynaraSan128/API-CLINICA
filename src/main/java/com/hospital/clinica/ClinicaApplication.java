package com.hospital.clinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * Ponto de entrada da aplicação Spring Boot.
 * A anotação configura automaticamente os recursos principais do Spring.
 */
public class ClinicaApplication {

	/** Inicia a aplicação e o servidor web. */
	public static void main(String[] args) {
		SpringApplication.run(ClinicaApplication.class, args);
	}

}
