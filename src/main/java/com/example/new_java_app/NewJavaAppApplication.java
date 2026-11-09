package com.example.new_java_app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication
@Tag(name = "BASE", description = "Base Endpoint for the Spring Boot Application")
@RestController
public class NewJavaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewJavaAppApplication.class, args);
	}

	@Operation(summary = "Base Endpoint", description = "Returns a welcome message for the base endpoint")
	@GetMapping("/")
    public String baseResponse() {
         return "Welcome to the Spring Boot API! This is the base endpoint of the application.";
	}

}
