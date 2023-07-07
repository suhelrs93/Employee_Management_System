package com.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	// get Employees by Department ID
	// Returns a list of Employee entities associated with the given department ID
	// Uses a custom JPQL query to select employees based on the department ID parameter
	@Query("from Employee where dept=(from Department where deptId=:d)")
	List<Employee> getEmployeeByDeptId(@Param("d") int deptId);
	
	// get Employees by Name
	// Returns a list of Employee entities with a matching name
	// Method name is derived from the field name "empName" in the Employee entity
	List<Employee> getByEmpName(String name);

	// get Employees by Email
	// Returns a list of Employee entities with a matching email
	// Method name is derived from the field name "email" in the Employee entity
//	List<Employee> getByEmail(String email);
}
