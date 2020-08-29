package com.promotion.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EngineApplication {

	public static void main(String[] args) {
		System.out.println("hi");
		SpringApplication.run(EngineApplication.class, args);
	}

}
