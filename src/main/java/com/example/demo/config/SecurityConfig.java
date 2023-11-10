package com.example.demo.config;

import org.springframework.context.annotation.Bean;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.demo.filter.LoggingAfterFilter;
import com.example.demo.filter.RequestValidationBeforeFilter;

import jakarta.servlet.http.HttpServletRequest;

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
	
	// our custom configuration
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
	{
		   CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
	       requestHandler.setCsrfRequestAttributeName("_csrf");
	        
	        
	        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
	             @Override
	             public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
	                CorsConfiguration config = new CorsConfiguration();
	                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	                config.setAllowedMethods(Collections.singletonList("*"));
	                config.setAllowCredentials(true);
	                config.setAllowedHeaders(Collections.singletonList("*"));
	                config.setExposedHeaders(Arrays.asList("Authorization"));
	                config.setMaxAge(3600L);
	                return config;
	             }
	                }))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class) // doesn't allow customer with name ending with "test"
                .addFilterAfter(new LoggingAfterFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")           // will be accessible to user having "VIEWACCOUNT" authority
                .requestMatchers("/myBalance").hasAnyAuthority("VIEWBAL", "ViewBal") // will give 403 not authorized error as we have not configured this authority for any user  
                .requestMatchers("/customerList").hasRole("USER")
                .requestMatchers("/myCards").authenticated()                        // will be accessible to user having any or no authority/role   
                .requestMatchers("/contactUs", "/register").permitAll()             // this will not require credentials
                .and().formLogin(withDefaults()).httpBasic(withDefaults());
		
		return http.build();
	}


    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
	 
}
