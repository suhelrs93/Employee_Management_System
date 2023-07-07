package com.ems.model;

import java.time.LocalDate;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ems.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveDTO {
	
	private int applicationId; // Unique identifier for the leave
	
	@NotNull(message = "Subject of leave cannot be null")
	@Size(min = 3, message = "Min. 3 characters required")
	@Size(max = 50, message = "Max. 50 characters allowed")
	private String subject; // subject or type of leave 
	
	@NotNull(message = "From_Date cannot be null")
	private LocalDate from_Date; // From which date the leave is required
	
	@NotNull(message = "To_Date cannot be null")
	private LocalDate to_Date; // By which date the leave is required
	
	@NotNull(message = "Description message cannot be null")
	@Size(min = 30, message = "Min. 30 characters required")
	@Size(max = 100, message = "Max. 100 characters allowed")
	private String message; // Description message for leave
	
	@NotNull(message = "Total_Days of leave cannot be null")
	private int total_Days; // total days for leave
	
//	private String status;
	
	@ManyToOne
	private Employee employee;
}
