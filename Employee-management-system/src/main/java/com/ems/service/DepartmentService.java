package com.ems.service;

import java.util.List;

import com.ems.entity.Department;
import com.ems.model.DepartmentDTO;
import com.ems.model.EmployeeDTO;

public interface DepartmentService {
	
	String createDepartment(Department department);
	// This method is used to save the details of a new department.
	// It takes a Department object as a parameter, which contains the department details to be saved.
	// The return type is a String, which represents a success message or any error message related to the department creation.

	DepartmentDTO saveDepartment(Department department);
	// This method is used to save the details of a department and return a DepartmentDTO object.
	// It takes a Department object as a parameter, which contains the department details to be saved.
	// The return type is a DepartmentDTO, which is a data transfer object representing the saved department.

	EmployeeDTO assignEmpToDept(int empId, int deptId);
	// This method is used to assign an employee to a certain department.
	// It takes two integer parameters: empId specifies the employee ID, and deptId specifies the department ID.
	// The method returns an EmployeeDTO, which represents the updated employee details after the assignment.

	DepartmentDTO getDepartmentById(int id);
	// This method is used to retrieve the details of a department based on the provided department ID.
	// It takes an integer ID as a parameter, which specifies the department ID to be retrieved.
	// The return type is a DepartmentDTO, which is a data transfer object representing the retrieved department.
	
	List<DepartmentDTO> getAllDepartments();//method used to get all Departments details
	
	//method to update existing Department details
	DepartmentDTO updateDepartment(int id, Department department);
	
	//method used to delete department details by id
	String deleteDepartmentById(int id);
	
	//method used to delete all departments details
	void deleteAllDepartments();
	
	//method used to retrieve department detail using department name
	List<DepartmentDTO> getDeptByDeptName(String name);
}

