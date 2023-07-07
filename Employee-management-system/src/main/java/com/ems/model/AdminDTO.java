package com.ems.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDTO extends UserDTO{

	@NotNull(message="Admin Name cannot be null")
	private String adminName;
	
	@NotNull(message="Email cannot be null")
	@Email(message="Please enter proper email address")
	private String email;
}
