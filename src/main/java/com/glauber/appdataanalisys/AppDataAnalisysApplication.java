package com.glauber.appdataanalisys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AppDataAnalisysApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppDataAnalisysApplication.class, args);
	}
}
