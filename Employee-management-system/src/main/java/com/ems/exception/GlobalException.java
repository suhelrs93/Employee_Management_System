package com.ems.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	//handle method for specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception,
			WebRequest request)
	{
		// Create an instance of ErrorDetails with timestamp, error message, and error details
		ErrorDetails errorDetails =new ErrorDetails(new Date(), exception.getMessage(),
				request.getDescription(false));
		
		// Return a ResponseEntity with the ErrorDetails object and HTTP status code of NOT_FOUND
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PropertyAlreadyExistsException.class)
	public ResponseEntity<?> propertyAlreadyExistsHandling(PropertyAlreadyExistsException exception)
	{
		// Return a ResponseEntity with the ErrorDetails object and HTTP status code of NOT_FOUND
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
