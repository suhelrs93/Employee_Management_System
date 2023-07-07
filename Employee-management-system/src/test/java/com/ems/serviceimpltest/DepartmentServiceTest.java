package com.ems.serviceimpltest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ems.entity.Department;
import com.ems.model.DepartmentDTO;
import com.ems.repository.DepartmentRepository;
import com.ems.service.DepartmentService;
import com.ems.util.DepartmentConverter;

@SpringBootTest
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private DepartmentConverter converter;
	
	@MockBean
	private DepartmentRepository deptRepository;
	
	private Department dept;
	
	@BeforeEach
	void init()
	{
		dept = Department.builder().deptName("IT").totalEmp(20).location("pune").build();
	}
	
	@Test
	@DisplayName("Testing create department method")
	void testcreateDepartment()
	{
		Mockito.when(deptRepository.save(dept)).thenReturn(dept);
		assertEquals(deptService.createDepartment(dept), "Department saved successfully.");
		
	}
	
	@Test
	@DisplayName("Testing Save department method")
    void testSaveDepartment() 
	{
        Department department = new Department();
        department.setDeptId(1);
        department.setDeptName("IT");
        department.setTotalEmp(10);
        department.setLocation("India");

        Mockito.when(deptRepository.save(department)).thenReturn(department);
                
        Department result = converter.convertToDeptEntity(deptService.saveDepartment(department));

        assertEquals(1, result.getDeptId());
        assertEquals("IT", result.getDeptName());
        assertEquals(10, result.getTotalEmp());
        assertEquals("India", result.getLocation());
	}
	
	@Test
	@DisplayName("Testing get department by id method")
	void testGetDeptById()
	{
		Optional<Department> opDept = Optional.of(dept);
		Department dept2 = opDept.get();
		
		Mockito.when(deptRepository.findById(dept.getDeptId())).thenReturn(opDept);
		DepartmentDTO dDto = converter.convertToDeptDTO(dept2);
		assertThat(deptService.getDepartmentById(dept2.getDeptId()).getDeptName()).isEqualTo(dDto.getDeptName());
				
	}
	
	@Test
	@DisplayName("Negative test case")
	void testNegativeGetDepartmentById() {
	    Optional<Department> op = Optional.of(dept);
	    Mockito.when(deptRepository.findById(dept.getDeptId())).thenReturn(op);

	    DepartmentDTO departmentDTO = converter.convertToDeptDTO(dept);

	    assertThat(deptService.getDepartmentById(dept.getDeptId()).getDeptName()).isNotEqualTo(departmentDTO.getDeptName());
	}
}
	
