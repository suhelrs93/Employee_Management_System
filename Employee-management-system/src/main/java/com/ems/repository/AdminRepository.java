package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	

}
