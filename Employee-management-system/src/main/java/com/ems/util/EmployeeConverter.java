package com.ems.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ems.entity.Employee;
import com.ems.model.EmployeeDTO;

@Component
public class EmployeeConverter {
	
	/**
	 * Converts an Employee entity to an EmployeeDTO.
	 *
	 * @parameter emp the Employee entity
	 * @return the converted EmployeeDTO
	 */
	public EmployeeDTO convertToEmpDTO(Employee emp)
	{
		EmployeeDTO eDto = new EmployeeDTO();
		if(emp!=null)
		{
			BeanUtils.copyProperties(emp, eDto);
		}
		return eDto;
	}
	
	
	/*
	 * Converts an EmployeeDTO to an Employee entity.
	 *
	 * @parameter eDto the EmployeeDTO
	 * @return the converted Employee entity
	 */
	public Employee convertToEntity(EmployeeDTO eDto)
	{
		Employee emp = new Employee();
		if(eDto!=null)
		{
			BeanUtils.copyProperties(eDto, emp);
		}
		return emp;
	}

}
