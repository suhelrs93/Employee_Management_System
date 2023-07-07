package com.ems.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.entity.Admin;
import com.ems.entity.Role;
import com.ems.exception.PropertyAlreadyExistsException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.model.AdminDTO;
import com.ems.repository.AdminRepository;
import com.ems.repository.RoleRepository;
import com.ems.service.AdminService;
import com.ems.util.AdminConverter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j //adding logger using annotation from lombok
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AdminConverter converter;
	
	@Override
	public AdminDTO saveAdmin(Admin admin) throws PropertyAlreadyExistsException{
		
		List<Admin> admins = adminRepository.findAll();
		for(Admin a :admins)
		{
			if(a.getEmail().equals(admin.getEmail()))
			{
				throw new PropertyAlreadyExistsException("Email adready exists.");
			}
			if(a.getUserName().equals(admin.getUserName()))
			{
				throw new PropertyAlreadyExistsException("User_Name already exists.");
			}
		}
		
		admin.setUserName(admin.getUserName());
		admin.setPassword(admin.getPassword());
		Role role =roleRepository.findById(1).get();
		admin.setRole(role);
		
		adminRepository.save(admin);
		log.info("New admin details added at "+new Date());
		return converter.convertToAdminDTO(admin);
	}

	//method to get/fetch admin details using id
	@Override
	public AdminDTO getAdminById(int id) throws ResourceNotFoundException{
		Admin admin = adminRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Admin", "Id", id));
		log.info("Admin with id "+id+" details fetched at "+new Date());
		return converter.convertToAdminDTO(admin);
	}

	@Override
	public List<AdminDTO> getAllAdmins() {
		List<Admin> admins = adminRepository.findAll();
		
		List<AdminDTO> adminDTO = new ArrayList<>();
		for(Admin a: admins)
		{
			adminDTO.add(converter.convertToAdminDTO(a));
		}
		log.info("All admin details fetched at "+new Date());
		return adminDTO;
	}

	@Override
	public AdminDTO updateAdmin(int id, Admin admin) throws ResourceNotFoundException{
		//we need to check whether admin exist with id or not in DB
		Admin existAdmin = adminRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Admin", "Id", id));
		
		List<Admin> admins = adminRepository.findAll();
		for(Admin a :admins)
		{
			if(a.getId()!=id)
			{
			if(a.getEmail().equals(admin.getEmail()))
			{
				throw new PropertyAlreadyExistsException("Email adready exists.");
			}
			if(a.getUserName().equals(admin.getUserName()))
			{
				throw new PropertyAlreadyExistsException("User_Name already exists.");
			}
			}
		}
		
		//we will get data from the user and set it in existing admin
		existAdmin.setAdminName(admin.getAdminName());
		existAdmin.setEmail(admin.getEmail());
		existAdmin.setUserName(admin.getUserName());
		existAdmin.setPassword(admin.getPassword());
		
		adminRepository.save(existAdmin);
		log.info("Admin with id "+id+" details updated at "+new Date());
		return converter.convertToAdminDTO(existAdmin);
	}

	@Override
	public String deleteAdminById(int id) throws ResourceNotFoundException{
		String msg=null;
		
		Optional<Admin> opAdmin = adminRepository.findById(id);
		if(opAdmin.isPresent())
		{
			adminRepository.deleteById(id);
			msg = "Record deleted successfully";
			log.info("Admin with id "+id+" deleted at "+new Date());
		}
		else
		{
			log.error("Admin with id "+id+ " not found.");
			throw new ResourceNotFoundException("Admin","ID",id);
		}
		return msg;
	}

}
