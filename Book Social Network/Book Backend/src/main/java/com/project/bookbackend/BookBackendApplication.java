package com.project.bookbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookBackendApplication.class, args);
	}
}
