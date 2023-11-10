package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug=true)
public class BankAppSecured1Application {

	public static void main(String[] args) {
		SpringApplication.run(BankAppSecured1Application.class, args);
	}

}
