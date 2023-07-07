package com.ems.service;

import java.util.List;

import com.ems.entity.Admin;
import com.ems.model.AdminDTO;

public interface AdminService {
	
	AdminDTO saveAdmin(Admin admin);
	
	AdminDTO getAdminById(int id);

	List<AdminDTO> getAllAdmins();
	
	AdminDTO updateAdmin(int id, Admin admin);
	
	String deleteAdminById(int id);
}
