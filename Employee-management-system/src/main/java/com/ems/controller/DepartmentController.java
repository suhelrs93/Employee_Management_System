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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Department;
import com.ems.model.DepartmentDTO;
import com.ems.model.EmployeeDTO;
import com.ems.service.DepartmentService;
import com.ems.util.DepartmentConverter;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "http://localhost:4200/")
public class DepartmentController {
	
	@Autowired
	private DepartmentService deptService; // Autowired dependency injection for the DepartmentService
	
	@Autowired
	private DepartmentConverter converter; // // Autowired dependency injection for the DepartmentConverter
	
//	@PostMapping("/createDepartment")
//	public String createDepatment(@RequestBody Department department)
//	{
		// Call the createDepartment method of the DepartmentService to create a new department
//		return deptService.createDepartment(department);
//	}

	//@PostMapping("/saveDepartment")
	@PostMapping
	public DepartmentDTO saveDepartment(@Valid @RequestBody DepartmentDTO ddto)
	{
		// Convert the DepartmentDTO to a Department entity using the DepartmentConverter
		Department dept = converter.convertToDeptEntity(ddto);
				
		// Call the saveDepartment method of the DepartmentService to save the department
		return deptService.saveDepartment(dept);
	}
	
	@PostMapping("/assignEmp/{empId}/assignToDept/{deptId}")
	public EmployeeDTO assignEmpToDept(@PathVariable("empId") int empId,
			@PathVariable("deptId") int deptId)
	{
		// Call the assignEmpToDept method of the EmployeeService to assign an employee to a department
		return deptService.assignEmpToDept(empId, deptId);
	}
	
	//@GetMapping("/getDepartment/{deptId}")
	@GetMapping("/{deptId}")
	public DepartmentDTO getDepartmentById(@PathVariable("deptId") int id)
	{
		// Call the getDepartmentById method of the DepartmentService to retrieve a department by its ID
		return deptService.getDepartmentById(id);
	}
	
	//@GetMapping("/getAllDepartments")
	@GetMapping
	public List<DepartmentDTO> getAllDepartments()
	{
		// Call the getAllDepartments method of the DepartmentService to retrieve all departments present in DB
		return deptService.getAllDepartments();
	}
	
	//@PutMapping("/updateDepartment/{id}")
	@PutMapping("/{deptId}")
	public DepartmentDTO updateDepartment(@PathVariable("deptId") int id,
			@Valid @RequestBody DepartmentDTO ddto)
	{
		Department d = converter.convertToDeptEntity(ddto);
		
		//updating department details by calling updateDepartment method of the DepartmentService
		return deptService.updateDepartment(id, d);
	}
	
	//@DeleteMapping("/deleteDeptById/{id}")
	@DeleteMapping("/{id}")
	public void deleteDepartmentById(@PathVariable("id") int id)
	{
		//deleting particular department by Id calling deleteDepartmentById method of the DepartmentService
		deptService.deleteDepartmentById(id);
	}
	
	//@DeleteMapping("/deleteAllDepts")
	@DeleteMapping
	public ResponseEntity<String> deleteAllDepartment()
	{
		//deleting all departments present in department by calling deleteAllDepartments method of the DepartmentService 
		deptService.deleteAllDepartments();
		return new ResponseEntity<String>("All department details deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/getDeptByName/{name}")
	public List<DepartmentDTO> getDeptByName(@PathVariable("name") String name)
	{
		// Call the getDeptByDeptName method of the DepartmentService to retrieve a department by its name
		return deptService.getDeptByDeptName(name);
	}
}
