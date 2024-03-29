package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepo;

@RestController
public class LoginController {
	
	@Autowired
	CustomerRepo customerRepo;

	/*
	 * here we are registering user and password encoder is NoOpPasswordEncoder
	 */
//	@PostMapping("/register")
//	public ResponseEntity<String> registerUser(@RequestBody Customer customer)
//	{
//		Customer savedCustomer =  null;
//		ResponseEntity<String> response = null;
//		try
//		{
//			savedCustomer = customerRepo.save(customer); // here we are saving user in db
//			if(savedCustomer.getId() > 0)
//			{
//				response = ResponseEntity.status(HttpStatus.CREATED)
//		                 .body("User created successfully !!");
//			}
//		}
//		catch(Exception ex)
//		{
//			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					                 .body("An exception occured due to " + ex.getMessage());
//		}
//		return response;
//	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	/*
	 * here we are registering user and password encoder is BCryptPasswordEncoder
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer)
	{
		Customer savedCustomer =  null;
		ResponseEntity<String> response = null;
		try
		{
			String hashPwd = passwordEncoder.encode(customer.getPassword());
			customer.setPassword(hashPwd);
			savedCustomer = customerRepo.save(customer); // here we are saving user in db
			if(savedCustomer.getId() > 0)
			{
				response = ResponseEntity.status(HttpStatus.CREATED)
		                 .body("User created successfully !!");
			}
		}
		catch(Exception ex)
		{
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					                 .body("An exception occured due to " + ex.getMessage());
		}
		return response;
	}
}
