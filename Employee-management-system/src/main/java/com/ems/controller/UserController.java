package com.ems.controller;

import java.util.Date;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.User;
import com.ems.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public String validate(@Valid @RequestBody User user) throws ServletException
	{
		String jwtToken = "";
		if(user.getUserName()==null && user.getPassword()==null && user.getRole()==null)
		{
			throw new ServletException("Please fill in userName, password and role");
		}
		
		user = userService.login(user.getUserName(), user.getPassword());
		
		if(user==null)
		{
			throw new ServletException("User details not found");
		}
		
		jwtToken=Jwts.builder().setSubject(user.getUserName())
				.claim("roles", user.getRole().getRoleName()).
		setIssuedAt(new Date()).setExpiration(
            	new Date(System.currentTimeMillis()+ 
                        7 * 24 * 60 * 60 * 1000)) // 1 week
		.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		
		return jwtToken;
	}
}
