package com.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> 
{
	
}
