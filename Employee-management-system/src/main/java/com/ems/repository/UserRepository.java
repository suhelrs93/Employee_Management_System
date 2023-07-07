package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ems.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	// This interface extends the JpaRepository interface, which provides basic CRUD (Create, Read, Update, Delete) operations for the User entity.
	// The User entity is associated with the User table in the database, and the primary key type is Integer.
	
	// additional methods are defined here
	//method to find user details using userName and password
	User findByUserNameAndPassword(String userName, String password);
}
