package com.github.rezakaramad.mavizcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Main application class for the Maviz Care project. This class bootstraps the Spring Boot
 * application.
 */
@SpringBootApplication
@EntityScan("com.github.rezakaramad.mavizcare.entity")
public class MavizCareApplication {

  public static void main(String[] args) {
    SpringApplication.run(MavizCareApplication.class, args);
  }
}
