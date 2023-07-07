package com.ems.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.entity.Department;
import com.ems.entity.Employee;
import com.ems.entity.Role;
import com.ems.exception.PropertyAlreadyExistsException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.model.EmployeeDTO;
import com.ems.repository.DepartmentRepository;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.RoleRepository;
import com.ems.service.EmployeeService;
import com.ems.util.EmployeeConverter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j //adding logger using annotation from lombok
public class EmployeeServiceImpl implements EmployeeService{

	//logger statically created
	private static final Logger log = LoggerFactory.getLogger(Employee.class);
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@Autowired
	private DepartmentRepository deptRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EmployeeConverter converter;
	
	@Override
	public String createEmployee(Employee employee) {
		// Set the employee details from the input
		employee.setUserName(employee.getUserName());
		employee.setPassword(employee.getPassword());
		
		Role role = roleRepository.findById(2).get();
		employee.setRole(role);
		
		// Save the employee details
		empRepository.save(employee);
		
		// Return success or failure message
		if(employee!=null)
		{
			return "Employee saved successfully.";
		}
		
		return "Employee details not saved.";
		
	}
	
	//method to save new employee details and return employee details
	@Override
	public EmployeeDTO saveEmployee(Employee employee) throws PropertyAlreadyExistsException{
		
		List<Employee> employees = empRepository.findAll();
		for(Employee e :employees)
		{
			if(e.getEmail().equals(employee.getEmail()))
			{
				throw new PropertyAlreadyExistsException("Email adready exists.");
			}
			if(e.getContact().equals(employee.getContact()))
			{
				throw new PropertyAlreadyExistsException("Phone number already exists.");
			}
			if(e.getUserName().equals(employee.getUserName()))
			{
				throw new PropertyAlreadyExistsException("User_Name already exists.");
			}
		}
		
		// Set the employee details from the input
		employee.setUserName(employee.getUserName());
		employee.setPassword(employee.getPassword());
		
		Role role = roleRepository.findById(2).get();
		employee.setRole(role);
		
		// Save the employee details
		empRepository.save(employee);
		
		//updating log file with date and time
		log.info("New employee details added at "+new Date());
		
		// Convert and return the saved employee as EmployeeDTO
		return converter.convertToEmpDTO(employee);
	}

	//method to get/fetch employee details using id
	@Override
	public EmployeeDTO getEmployeeById(int id) throws ResourceNotFoundException{
		// Retrieve the employee from the repository based on the provided ID
		Employee emp = empRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Employee", "Id", id));
		
		//updating log file with date and time
		log.info("Employee with id "+id+" details fetched at "+new Date());
		
		// Convert and return the retrieved employee as EmployeeDTO
		return converter.convertToEmpDTO(emp);
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		// Retrieve all employees from the repository
		List<Employee> employees = empRepository.findAll();
		
		// Convert the employees to EmployeeDTOs
		List<EmployeeDTO> employeeDTO = new ArrayList<>();
		for(Employee e: employees)
		{
			employeeDTO.add(converter.convertToEmpDTO(e));
		}
		
		//updating log file with date and time
		log.info("All employee details fetched at "+new Date());
		return employeeDTO;
	}

	@Override
	public EmployeeDTO updateEmployee(int id, Employee employee) throws ResourceNotFoundException{
		//we need to check whether employee exist with id or not in DB
		Employee existEmp = empRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Employee", "Id", id));
		
		List<Employee> employees = empRepository.findAll();
		for(Employee e :employees)
		{
			if(e.getId()!=id)
			{
				if(e.getEmail().equals(employee.getEmail()))
				{
					throw new PropertyAlreadyExistsException("Email adready exists.");
				}
				if(e.getContact().equals(employee.getContact()))
				{
					throw new PropertyAlreadyExistsException("Phone number already exists.");
				}
				if(e.getUserName().equals(employee.getUserName()))
				{
					throw new PropertyAlreadyExistsException("User_Name already exists.");
				}
			}
		}
		
		//we will get data from the client and set it in existing employee
		existEmp.setEmpName(employee.getEmpName());
		existEmp.setSalary(employee.getSalary());
		existEmp.setContact(employee.getContact());
		existEmp.setEmail(employee.getEmail());
		existEmp.setDesignation(employee.getDesignation());
		existEmp.setDoj(employee.getDoj());
		existEmp.setUserName(employee.getUserName());
		existEmp.setPassword(employee.getPassword());
		
		// Save the updated employee details
		empRepository.save(existEmp);
		
		//updating log file with date and time
		log.info("Employee with id "+id+" details updated at "+new Date());
		
		// Convert and return the updated employee as EmployeeDTO
		return converter.convertToEmpDTO(existEmp);
	}

	@Override
	public String deleteEmployeeById(int id) throws ResourceNotFoundException{
		String msg=null;
		
		Optional<Employee> opEmp = empRepository.findById(id);
		if(opEmp.isPresent())
		{
			
			if(opEmp.get().getDept()!=null)
			{
				Department d =deptRepository.findById(opEmp.get().getDept().getDeptId()).get();
				d.setTotalEmp(d.getTotalEmp()-1);
				deptRepository.save(d);
			}
			empRepository.deleteById(id);
			
			msg = "Record deleted successfully";
			
			//updating log file with date and time
			log.info("Employee with id "+id+" deleted at "+new Date());
		}
		else
		{
			//updating log file with date and time as error
			log.error("Employee with id "+id+ " not found.");
			throw new ResourceNotFoundException("Employee","ID",id);
		}
		return msg;
	}

	@Override
	public void deleteAllEmployees() {
		// Delete all employees
		empRepository.deleteAll();
		
	}


	//query method to get all employee details working under a department using department id
	@Override
	public List<EmployeeDTO> getEmpByDeptId(int deptId) {
		// Retrieve employees of a specific department from the repository
		List<Employee> emps = empRepository.getEmployeeByDeptId(deptId);
		List<EmployeeDTO> dto = new ArrayList<>();
		
		// Convert the employees to EmployeeDTOs
		for(Employee e : emps)
		{
			dto.add(converter.convertToEmpDTO(e));
		}
		return dto;
	}

	//query method to get employee details using employee name
	@Override
	public List<EmployeeDTO> getEmpByEmpName(String name) {
		// Retrieve employees with a specific name from the repository
		List<Employee> emps = empRepository.getByEmpName(name);
		List<EmployeeDTO> dto = new ArrayList<>();
		
		// Convert the employees to EmployeeDTOs
		for(Employee e : emps)
		{
			dto.add(converter.convertToEmpDTO(e));
		}
		return dto;
	}

	
}
