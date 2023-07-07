package com.ems;

//Import necessary classes from the Spring Boot framework
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.ems.config.JwtFilter;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		
		FilterRegistrationBean registerBean = new FilterRegistrationBean();
		registerBean.setFilter(new JwtFilter());
		registerBean.addUrlPatterns("/api/*");
		return registerBean;
	}
	
	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
		
		// Print a message to indicate that the application is running
		System.out.println("Employee Management System is running...");
	}

}
