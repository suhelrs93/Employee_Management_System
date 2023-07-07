package com.ems.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HolidayList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // Unique identifier for the holiday
	
	@Column(nullable = false)
	private LocalDate date; // date of holiday

	@Column(length = 8, nullable = false)
	private String day; // day of holiday 
	
	@Column(length = 20, nullable = false)
	private String remark; // Remark for why is a holiday
	
		
}
