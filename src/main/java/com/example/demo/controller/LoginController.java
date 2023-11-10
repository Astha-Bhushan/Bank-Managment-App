package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepo;

@RestController
public class LoginController {
	
	@Autowired
	CustomerRepo customerRepo;

	/*
	 * here we are registering user and password encoder is NoOpPasswordEncoder i.e
	 * we are saving password in simple plain text format.
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
	 * here we are registering user by BCryptPasswordEncoder (password encoder)
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer)
	{
		Customer savedCustomer =  null;
		ResponseEntity<String> response = null;
		try
		{
			System.out.println(customer);
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
	
	@GetMapping("/customerList")
	public Iterable<Customer> customerList()
	{
		Iterable<Customer> list = customerRepo.findAll();
		return list;
	}
	
	@RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = customerRepo.findByEmail(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }
    }
	
	
}
