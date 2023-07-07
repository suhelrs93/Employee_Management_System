package com.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	// get Department by department name
	// Returns the Department entity associated with the given department name
	List<Department> getByDeptName(String name);
	
}
