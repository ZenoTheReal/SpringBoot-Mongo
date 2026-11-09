package com.example.new_java_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
@Tag(name = "BASE", description = "Base Endpoint for the Spring Boot Application")
@RestController
public class NewJavaAppApplication {

	public static void main(String[] args) {
		loadDotEnv();
		SpringApplication.run(NewJavaAppApplication.class, args);
	}

	/**
	 * Automatically loads variables from .env into System properties so
	 * MongoDB and other services work seamlessly whether started via Gradle,
	 * npm, or the IDE directly.
	 */
	private static void loadDotEnv() {
		Path envPath = Path.of(".env");
		if (Files.exists(envPath)) {
			try {
				for (String line : Files.readAllLines(envPath)) {
					line = line.trim();
					if (!line.isEmpty() && !line.startsWith("#") && line.contains("=")) {
						int idx = line.indexOf('=');
						String key = line.substring(0, idx).trim();
						String value = line.substring(idx + 1).trim();
						// Only set if not already set in OS environment or System properties
						if (System.getProperty(key) == null && System.getenv(key) == null) {
							System.setProperty(key, value);
						}
					}
				}
				System.out.println(">>> Loaded configuration from .env successfully.");
			} catch (IOException e) {
				System.err.println(">>> Failed to load .env: " + e.getMessage());
			}
		}
	}

	@Operation(summary = "Base Endpoint", description = "Returns a welcome message for the base endpoint")
	@GetMapping("/")
    public String baseResponse() {
         return "Welcome to the Spring Boot API! This is the base endpoint of the application.";
	}

}
