package com.ems.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ems.entity.Department;
import com.ems.model.DepartmentDTO;

@Component
public class DepartmentConverter {
	
	//convert from department to departmentDTO
	public DepartmentDTO convertToDeptDTO(Department dept)
	{
		DepartmentDTO dDto = new DepartmentDTO();
		if(dept!=null)
		{
			BeanUtils.copyProperties(dept, dDto);
		}
		return dDto;
	}
	
	
	//convert from departmentDTO to department
	public Department convertToDeptEntity(DepartmentDTO dDto)
	{
		Department dept = new Department();
		if(dDto!=null)
		{
			BeanUtils.copyProperties(dDto, dept);
		}
		return dept;
	}

}
