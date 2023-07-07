package com.ems.service;

import java.util.List;

import com.ems.entity.Employee;
import com.ems.model.EmployeeDTO;

public interface EmployeeService {

	String createEmployee(Employee employee);
	// This method is used to save the details of a new employee.
	// It takes an Employee object as a parameter, which contains the employee details to be saved.
	// The return type is a String, which represents a success message or any error message related to the employee creation.

	EmployeeDTO saveEmployee(Employee employee);
	// This method is used to save the details of an employee and return an EmployeeDTO object.
	// It takes an Employee object as a parameter, which contains the employee details to be saved.
	// The return type is an EmployeeDTO, which is a data transfer object representing the saved employee.

	EmployeeDTO getEmployeeById(int id);
	// This method is used to retrieve the details of an employee based on the provided employee ID.
	// It takes an integer ID as a parameter, which specifies the employee ID to be retrieved.
	// The return type is an EmployeeDTO, which is a data transfer object representing the retrieved employee.

	List<EmployeeDTO> getAllEmployees();
	// This method is used to retrieve the details of all employees.
	// It returns a List of EmployeeDTO objects, which represent the details of all employees in the system.

	EmployeeDTO updateEmployee(int id, Employee employee);
	// This method is used to update the details of an existing employee.
	// It takes an integer ID and an Employee object as parameters.
	// The ID specifies the employee ID to be updated, and the Employee object contains the updated employee details.
	// The method returns an EmployeeDTO, which is a data transfer object representing the updated employee.

	String deleteEmployeeById(int id);
	// This method is used to delete an employee based on the provided employee ID.
	// It takes an integer ID as a parameter, which specifies the employee ID to be deleted.
	// The method returns a String, which represents a success message or any error message related to the employee deletion.

	void deleteAllEmployees();
	// This method is used to delete all employee details from the system.
	// It does not take any parameters and does not return a value.

	List<EmployeeDTO> getEmpByDeptId(int deptId);
	// This method is used to retrieve the details of employees belonging to a specific department.
	// It takes an integer deptId as a parameter, which specifies the department ID.
	// The method returns a List of EmployeeDTO objects, which represent the details of employees in the specified department.

	List<EmployeeDTO> getEmpByEmpName(String name);
	// This method is used to retrieve the details of employees based on their names.
	// It takes a String name as a parameter, which specifies the employee name.
	// The method returns a List of EmployeeDTO objects, which represent the details of employees with the specified name.

}
