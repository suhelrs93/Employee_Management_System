package com.ems.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDetails {
	
private Date timestamp; // Timestamp of when the error occurred
	
	private String message; // Error message
	
	private String details; // Additional details about the error
	
}
