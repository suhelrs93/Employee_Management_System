package com.ems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin extends User{

	@Column(length=30)
	private String adminName;
	
	@Column(length=50, nullable=false, unique=true)
	private String email;
	
}
