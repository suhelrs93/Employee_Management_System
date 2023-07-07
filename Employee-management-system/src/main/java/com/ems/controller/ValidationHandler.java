package com.ems.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		// Create a map to hold the field names and error messages
		Map<String, String> errors = new HashMap<>();
		
		// Iterate through all validation errors
		ex.getBindingResult().getAllErrors().forEach((error)->
		{
			String fieldName = ((FieldError) error).getField(); // Get the field name
			String errorMessage = error.getDefaultMessage(); // Get the error message
			errors.put(fieldName, errorMessage); // Add the field name and error message to the map
		});
		
		// Return a ResponseEntity with the errors map and HTTP status BAD_REQUEST
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
}

