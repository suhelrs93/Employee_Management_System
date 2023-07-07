package com.ems.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 5601809859131411579L;
	private String resourceName; // Name of the resource that was not found
	private String fieldName; // Field name that caused the resource not found exception
	private Object fieldValue; // Field value that caused the resource not found exception
	
	//using AllArgsConstructor to assigning values to all parameters
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		// Call the constructor of the parent RuntimeException class with a formatted error message
		// using the provided resource name, field name, and field value
		super(String.format("%s not found with %s : '%s'",resourceName,fieldName,fieldValue));
		
		// Set the values of the resource name, field name, and field value
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	
	
	
	
}	
