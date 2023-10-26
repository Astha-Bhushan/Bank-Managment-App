package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	/**
	// default configuration written in spring security to authenticate all endpoints.
    @Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests((requests)-> requests.anyRequest().authenticated());
		http.formLogin();
		http.httpBasic();
		return http.build();
	}
	**/
	
	// our default configuration
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
		    .authorizeHttpRequests()
		    .requestMatchers("/myAccount").authenticated() // this will be secured
		    .requestMatchers("/contactUs","/register").permitAll()     // this will not require credentials
		    .and().formLogin()
		    .and().httpBasic();
		
		return http.build();
	}
	
	/*
	 * this method tells spring security that our password are in text format and use 
	 * them as string only.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
	 
}
