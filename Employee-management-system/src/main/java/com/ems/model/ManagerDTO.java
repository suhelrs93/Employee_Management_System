package com.ems.model;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ems.entity.Department;
import com.ems.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDTO extends UserDTO{

	@NotNull(message = "Manager Name cannot be null")
	@Size(min = 2, message = "Min. 2 characters required")
	@Size(max = 30, message = "Max. 30 characters allowed")
	private String mName; // Name of the Manager
	
	@NotNull(message = "Salary cannot be null")
	private double salary; // Salary of the manager
	
	@NotNull(message = "Phone no. cannot be null")
	@Pattern(regexp = "^\\d{10}$", message = "phone number should have 10 digits")
	private String contact; // Contact number of the manager
	
	@NotNull(message = "Email cannot be null")
	@Email(message = "Please provide proper email")
	private String email; // Email address of the manager
	
	//@NotNull(message = "Designation cannot be null")
	private String designation; // Designation/role of the manager
	
	@NotNull(message = "Date of joining cannot be null")
	private LocalDate doj; // Date of joining of the manager
	
	@OneToOne
	private Department dept; // Department to which the manager belongs

	@OneToMany
	private List<Employee> employees; // one manager can have many employees
}

