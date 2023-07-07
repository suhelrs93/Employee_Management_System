package com.ems.serviceimpltest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ems.entity.Employee;
import com.ems.model.EmployeeDTO;
import com.ems.repository.EmployeeRepository;
import com.ems.service.EmployeeService;
import com.ems.util.EmployeeConverter;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private EmployeeConverter converter;
	
	@MockBean
	private EmployeeRepository empRepository;
	
	private Employee emp;
	
	@BeforeEach
	void init()
	{
		emp =Employee.builder().empName("Onkar").email("onkar@gmail.com").contact("9867945820").designation("Developer").
		salary(35000.0).doj(LocalDate.parse("2023-07-20")).userName("onkar").password("onkar123").build();
	}
	
	@Test
	@DisplayName("Testing create employee method")
	@Order(1)
	void testCreateEmployee()
	{
		Mockito.when(empRepository.save(emp)).thenReturn(emp);
		assertEquals(empService.createEmployee(emp), "Employee saved successfully." );
	}
	
	@Test
	@DisplayName("Testing update employee method")
	@Order(2)
	void testUpdateEmployee()
	{
		Optional<Employee> opEmp = Optional.of(emp);
		Employee emp1 = opEmp.get();
		
		Mockito.when(empRepository.findById(emp.getId())).thenReturn(opEmp);
		emp1.setEmpName("Onkar Katare");
		
		Mockito.when(empRepository.save(emp1)).thenReturn(emp1);
		EmployeeDTO eDto =converter.convertToEmpDTO(emp1);
		
		//assertEquals(eDto.getEmpName(), "Onkar Katare");
		assertThat(empService.updateEmployee(emp.getId(), emp1).getEmpName()).isEqualTo(eDto.getEmpName());
	}
	
	@Test
	@DisplayName("Testing get employee by id method")
	@Order(3)
	void testGetEmpById()
	{
		Optional<Employee> op = Optional.of(emp);
		Employee emp2 = op.get();
		
		Mockito.when(empRepository.findById(emp.getId())).thenReturn(op);
		
		EmployeeDTO eDto =converter.convertToEmpDTO(emp2);
		
		assertThat(empService.getEmployeeById(emp2.getId()).getEmail()).isEqualTo(eDto.getEmail());
	}
	
	@Test
	@DisplayName("Negative test case")
	@Order(4)
	void testNegativeGetEmpById()
	{
		Optional<Employee> op = Optional.of(emp);
		Employee emp2 = op.get();
		
		Mockito.when(empRepository.findById(emp.getId())).thenReturn(op);
		
		EmployeeDTO eDto =converter.convertToEmpDTO(emp2);
		
		assertThat(empService.getEmployeeById(emp2.getId()).getEmail()).isNotEqualTo(eDto.getEmail());
	}
}
