package com.ems.model;

import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ems.entity.Employee;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DepartmentDTO {

	private int deptId; // Department ID
	
	@NotNull(message = "Department Name cannot be null")
	@Size(max = 20, message = "Max. 20 characters allowed")
	private String deptName; // Department name
	
	@NotNull(message = "Number of employees cannot be null")
	private int totalEmp; // Total number of employees in the department
	
	@NotNull(message = "Department location cannot be null")
	@Size(max = 20, message = "Max. 20 characters allowed")
	private String location; // Location of the department
	
	@OneToMany
	private List<Employee> employees; // List of employees in the department
}
