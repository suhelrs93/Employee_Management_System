package com.ems.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ems.entity.Admin;
import com.ems.model.AdminDTO;

@Component
public class AdminConverter {

	//convert from admin entity to adminDTO
	public AdminDTO convertToAdminDTO(Admin admin)
	{
		AdminDTO aDto = new AdminDTO();
		if(admin!=null)
		{
			BeanUtils.copyProperties(admin, aDto);
		}
		return aDto;
	}
	
	
	//convert from adminDTO to admin entity
	public Admin convertToAdminEntity(AdminDTO aDto)
	{
		Admin admin = new Admin();
		if(aDto!=null)
		{
			BeanUtils.copyProperties(aDto, admin);
		}
		return admin;
	}
}
