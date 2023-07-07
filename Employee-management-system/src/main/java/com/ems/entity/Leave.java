package com.ems.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="leave_app")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Leave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int applicationId; // Unique identifier for the leave
	
	@Column(length = 50, nullable = false)
	private String subject; // subject or type of leave 
	
	@Column(nullable = false)
	private LocalDate from_Date; // From which date the leave is required
	
	@Column(nullable = false)
	private LocalDate to_Date; // By which date the leave is required
	
	@Column(length = 100, nullable = false)
	private String message; // Description message for leave
	
	@Column(nullable = false)
	private int total_Days; // total days for leave
	
//	@Column(length = 25, nullable = false)
//	private String status;
	
	@ManyToOne
	private Employee employee;
}
