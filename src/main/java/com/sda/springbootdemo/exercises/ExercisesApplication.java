package com.sda.springbootdemo.exercises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ExercisesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExercisesApplication.class, args);
	}

	@Bean
	@Profile("!production")
	public FlywayMigrationStrategy cleanMigrationStrategy() {
		return flyway -> {
			flyway.clean();
			flyway.migrate();
		};
	}
}
