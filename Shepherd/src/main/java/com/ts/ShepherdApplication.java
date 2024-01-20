package com.ts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class ShepherdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShepherdApplication.class, args);
	}

}
