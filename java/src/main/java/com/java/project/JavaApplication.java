package com.java.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaRepositories (basePackages = {"com.java.project.api.entity"})
@SpringBootApplication
@EnableJpaAuditing
public class JavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaApplication.class, args);
	}

}
