package com.example.JavaAndSpringIncubator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories
public class JavaAndSpringIncubator {

	public static void main(String[] args) {
		SpringApplication.run(JavaAndSpringIncubator.class, args);
	}

}
