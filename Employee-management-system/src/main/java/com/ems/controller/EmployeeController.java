package com.ems.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Employee;
import com.ems.entity.Leave;
import com.ems.model.EmployeeDTO;
import com.ems.model.LeaveDTO;
import com.ems.service.EmployeeService;
import com.ems.service.LeaveService;
import com.ems.util.EmployeeConverter;
import com.ems.util.LeaveConverter;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {
	
	@Autowired 
	private EmployeeService empService; // Autowired dependency injection for the EmployeeService
	
	@Autowired
	private EmployeeConverter converter; // Autowired dependency injection for the EmployeeConverter
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private LeaveConverter leaveConverter;
	
	//@PostMapping("/createEmployee")
//	@PostMapping
//	public String createEmployee(@RequestBody Employee employee)
//	{
//		// Call the createEmployee method of the EmployeeService to create a new employee
//		return empService.createEmployee(employee);
//	}

	//@PostMapping("/saveEmployee")
	@PostMapping("/employee")
	public EmployeeDTO saveEmployee(@Valid @RequestBody EmployeeDTO edto)
	{
		// Convert the EmployeeDTO to an Employee entity using the EmployeeConverter
		Employee emp = converter.convertToEntity(edto);
		
		// Call the saveEmployee method of the EmployeeService to save the employee
		return empService.saveEmployee(emp);
	}
	
	//@GetMapping("getEmployeeById/{empId}")
	@GetMapping("/employee/{empId}")
	public EmployeeDTO getEmployeeById(@PathVariable("empId") int id)
	{
		// Call the getEmployeeById method of the EmployeeService to retrieve an employee by its ID
		return empService.getEmployeeById(id);
	}
		
	//@GetMapping("/getAllEmployees")
	@GetMapping("/employee")
	public List<EmployeeDTO> getAllEmployees()
	{
		// Call the getAllEmployees method of the EmployeeService to retrieve all employees
		return empService.getAllEmployees();
	}
	
	//@PutMapping("/updateEmployee/{id}")
	@PutMapping("/api/{empId}")
	public EmployeeDTO updateEmployee(@PathVariable("empId") int id, 
			@Valid @RequestBody EmployeeDTO edto)
	{
		// Convert the EmployeeDTO to an Employee entity using the EmployeeConverter
		Employee e = converter.convertToEntity(edto);
		
		// Call the updateEmployee method of the EmployeeService to update the employee
		return empService.updateEmployee(id, e);
	}
	
	//@DeleteMapping("/deleteEmpById/{id}")
	@DeleteMapping("/api/{id}")
	public void deleteEmployeeById(@PathVariable("id") int id)
	{
		// Call the deleteEmployeeById method of the EmployeeService to delete an employee by its ID
		empService.deleteEmployeeById(id);
	}
	
	//@DeleteMapping("/deleteAllEmps")
	@DeleteMapping("/api")
	public ResponseEntity<String> deleteAllEmployee()
	{
		// Call the deleteAllEmployees method of the EmployeeService to delete all employees
		 empService.deleteAllEmployees();
		 
		// Return a ResponseEntity with a success message and HTTP status OK
		 return new ResponseEntity<String>("All employee details deleted successfully", HttpStatus.OK);
	}
	
	
	@GetMapping("/employee/getEmpByDeptId/{deptId}")
	public List<EmployeeDTO> getEmpByDeptId(@PathVariable("deptId") int deptId)
	{
		// Call the getEmpByDeptId method of the EmployeeService to retrieve employees by department ID
		return empService.getEmpByDeptId(deptId);
	}
	
	@GetMapping("/employee/getEmpByName/{name}")
	public List<EmployeeDTO> getEmpByName(@PathVariable("name") String name)
	{
		// Call the getEmpByEmpName method of the EmployeeService to retrieve employees by name
		return empService.getEmpByEmpName(name);
	}
	
	@PostMapping("/employee/applyLeave/{empId}")
	public ResponseEntity<?> applyForLeave(@PathVariable("empId") int empId,
			@RequestBody @Valid LeaveDTO leaveDto)
	{
		final Leave leave =leaveConverter.convertToLeaveEntity(leaveDto);
		return leaveService.applyForLeave(empId, leave);
	}
}
