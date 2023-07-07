package com.ems.model;

import java.time.LocalDate;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ems.entity.Department;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO extends UserDTO{
	
	
	@NotNull(message = "Employee Name cannot be null")
	@Size(min = 2, message = "Min. 2 characters required")
	private String empName; // Employee name
	
	@NotNull(message = "Salary cannot be null")
	private double salary; // Employee salary
	
	@NotNull(message = "Phone no. cannot be null")
	@Pattern(regexp = "^\\d{10}$", message = "phone number should have 10 digits")
	private String contact; // Employee contact number
	
	@NotNull(message = "Email cannot be null")
	@Email(message = "Please provide proper email")
	private String email; // Employee email address
	
	@NotNull(message = "Designation cannot be null")
	private String designation; // Employee designation
	
	@NotNull(message = "Date of joining cannot be null")
	private LocalDate doj; // Date of joining
	
	@ManyToOne
	private Department dept; // Department to which the employee belongs

}
