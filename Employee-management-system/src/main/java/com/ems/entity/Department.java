package com.ems.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="dept")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deptId; // Unique identifier for the department
	
	@Column(length=20, nullable = false, unique = true)
	private String deptName; // Name of the department
	
	@Column(nullable = false)
	private int totalEmp; // Total number of employees in the department
	
	@Column(length=20, nullable = false)
	private String location; // Location of the department
	
	@OneToMany
	@JsonIgnoreProperties("dept") //to ignore foreign key constraint error
	private List<Employee> employees; // List of employees associated with the department

	@Builder
	public Department(int deptId, String deptName, int totalEmp, String location) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.totalEmp = totalEmp;
		this.location = location;
	}

	
}
