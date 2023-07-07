package com.ems.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ems.entity.Manager;
import com.ems.model.ManagerDTO;

@Component
public class ManagerConverter {

	// Converts Manager entity to ManagerDTO.
	public ManagerDTO convertToManagerDTO(Manager manager)
	{
		ManagerDTO mDto = new ManagerDTO();
		if(manager!=null)
		{
			BeanUtils.copyProperties(manager, mDto);
		}
		return mDto;
	}
	
	// Converts ManagerDTO to Manager entity.
	public Manager convertToManagerEntity(ManagerDTO mDto)
	{
		Manager manager = new Manager();
		if(mDto!=null)
		{
			BeanUtils.copyProperties(mDto, manager);
		}
		return manager;
	}
}
