package com.ems.service;

import java.util.List;

import com.ems.entity.Employee;
import com.ems.entity.Manager;
import com.ems.model.EmployeeDTO;
import com.ems.model.LeaveDTO;
import com.ems.model.ManagerDTO;

public interface ManagerService {
	
	ManagerDTO addManager(Manager manager);
	// This method is used to save the details of an employee and return an EmployeeDTO object.
	// It takes an Employee object as a parameter, which contains the employee details to be saved.
	// The return type is an EmployeeDTO, which is a data transfer object representing the saved employee.
	
	ManagerDTO assignDeptToManager(int managerId, int deptId);

	ManagerDTO getManagerById(int id);
	// This method is used to retrieve the details of an employee based on the provided employee ID.
	// It takes an integer ID as a parameter, which specifies the employee ID to be retrieved.
	// The return type is an EmployeeDTO, which is a data transfer object representing the retrieved employee.

	List<ManagerDTO> getAllManagers();
	// This method is used to retrieve the details of all employees.
	// It returns a List of EmployeeDTO objects, which represent the details of all employees in the system.

	ManagerDTO updateManager(int id, Manager manager);
	// This method is used to update the details of an existing employee.
	// It takes an integer ID and an Employee object as parameters.
	// The ID specifies the employee ID to be updated, and the Employee object contains the updated employee details.
	// The method returns an EmployeeDTO, which is a data transfer object representing the updated employee.
	
	String deleteManagerById(int id);
	// This method is used to delete an employee based on the provided employee ID.
	// It takes an integer ID as a parameter, which specifies the employee ID to be deleted.
	// The method returns a String, which represents a success message or any error message related to the employee deletion.

//	List<LeaveDTO> getLeavesUnderManager(int managerId);
}
