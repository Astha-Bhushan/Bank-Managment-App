//package com.example.demo.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.repo.CustomerRepo;
//import com.example.demo.model.Customer;
//
///*
// * UserDetailsService is used by DaoAuthenticationProvider for retrieving a username, a password, and 
// * other attributes for authenticating with a username and password.
// * In DaoAuthentication provider we use our custom tables to store credentials.
// */
//@Service 
//public class CustomerService implements UserDetailsService {
//	
//	@Autowired
//	CustomerRepo customerRepo;
//
//	@Override // using data from customer table to authenticate
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	       String userName = null ;
//	       String password = null ;
//	       List<GrantedAuthority> authorities = null;
//	       List<Customer> customer = customerRepo.findByEmail(username);
//	       if(customer.size()==0)
//	       {
//	    	   throw new UsernameNotFoundException("User details not found for the user :" + username);
//	       }
//	       else
//	       {
//	    	   userName = customer.get(0).getEmail();
//	    	   password = customer.get(0).getPassword();
//	    	   System.out.println("astha " + username + " " + password);
//	    	   authorities = new ArrayList<>();
//	    	   authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
//	    	   
//	       }
//	       return new User(userName, password, authorities);
//	       
//	}
//
//}
