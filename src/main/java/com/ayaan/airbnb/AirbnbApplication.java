package com.ayaan.airbnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AirbnbApplication {
	public static void main(String[] args) {
		SpringApplication.run(AirbnbApplication.class, args);
	}	
}	