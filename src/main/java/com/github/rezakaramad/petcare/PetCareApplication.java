package com.github.rezakaramad.petcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Main application class for the Maviz Care project. This class bootstraps the Spring Boot
 * application.
 */
@SpringBootApplication
@EntityScan("com.github.rezakaramad.petcare.entity")
public class PetCareApplication {

  public static void main(String[] args) {
    SpringApplication.run(PetCareApplication.class, args);
  }
}
