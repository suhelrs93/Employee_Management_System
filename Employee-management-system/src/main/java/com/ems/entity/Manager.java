package com.ems.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends User{
	
	@Column(length = 30)
	private String mName; // Name of the manager
	
	@Column(length = 20, nullable = false)
	private double salary; // Salary of the manager
	
	@Column(length = 10, nullable = false, unique = true)
	private String contact; // Contact number of the manager
	
	@Column(length = 50, nullable = false, unique = true)
	private String email; // Email address of the manager
	
	@Column(length = 20, nullable = false)
	private String designation; // Designation/role of the manager
	
	@Column(nullable = false)
	private LocalDate doj; // Date of joining of the manager
	
	@OneToOne
	@JsonIgnoreProperties
	private Department dept; // Department to which the manager belongs

	@OneToMany
	@JsonIgnoreProperties
	private List<Employee> employees; // one manager can have many employees
}
