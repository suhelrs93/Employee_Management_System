package com.ems.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee extends User{
	
	@Column(length = 30, nullable = false)
	private String empName; // Name of the employee
	
	@Column(length = 20, nullable = false)
	private double salary; // Salary of the employee
	
	@Column(length = 10, nullable = false, unique = true)
	private String contact; // Contact number of the employee
	
	@Column(length = 50, nullable = false, unique = true)
	private String email; // Email address of the employee
	
	@Column(length = 20, nullable = false)
	private String designation; // Designation/role of the employee
	
	@Column(nullable = false)
	private LocalDate doj; // Date of joining of the employee
	
	@Column(nullable = false)
	private int remainingLeaves = 15;
	
	@ManyToOne
	@JsonIgnoreProperties("employees") //to ignore foreign key constraint error
	private Department dept; // Department to which the employee belongs

	@ManyToOne
	@JsonIgnoreProperties
	private Manager manager;
	
	@OneToMany
	private List<Leave> leave;	

	@Builder
	public Employee(int id, String userName, String password, Role role, String empName, double salary, String contact,
			String email, String designation, LocalDate doj) {
		super(id, userName, password, role);
		this.empName = empName;
		this.salary = salary;
		this.contact = contact;
		this.email = email;
		this.designation = designation;
		this.doj = doj;
	}

	
	
	
	
}
