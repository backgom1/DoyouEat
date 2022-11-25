package com.DoyouEat.DYEat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DyEatApplication {

	public static void main(String[] args) {
		SpringApplication.run(DyEatApplication.class, args);
	}

}
