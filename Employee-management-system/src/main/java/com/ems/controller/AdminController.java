package com.ems.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Admin;
import com.ems.model.AdminDTO;
import com.ems.service.AdminService;
import com.ems.util.AdminConverter;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminConverter Converter;
	
	//@PostMapping("/saveAdmin")
	@PostMapping
	public AdminDTO saveAdmin(@Valid @RequestBody AdminDTO adto)
	{
		Admin admin = Converter.convertToAdminEntity(adto);
		return adminService.saveAdmin(admin);
	}
	
	//@GetMapping("getAdminById/{adminId}")
	@GetMapping("/{adminId}")
	public AdminDTO getAdminById(@PathVariable("adminId") int id)
	{
		return adminService.getAdminById(id);		
	}

	//@GetMapping("/getAllAdmins")
	@GetMapping
	public List<AdminDTO> getAllAdmins()
	{
		return adminService.getAllAdmins();
	}
	
	//@PutMapping("/updateAdmin/{adminId}")
	@PutMapping("/{adminId}")
	public AdminDTO updateAdmin(@PathVariable("adminId") int id,
			@Valid @RequestBody AdminDTO adto)
	{
		Admin a = Converter.convertToAdminEntity(adto);
		return adminService.updateAdmin(id, a);
	}
	
	//@DeleteMapping("/deleteAdminById/{adminId}")
	@DeleteMapping("/{adminId}")
	public ResponseEntity<String> deleteAdminById(@PathVariable("adminId") int id)
	{
		return new ResponseEntity<String>(adminService.deleteAdminById(id),
				HttpStatus.OK);
	}
}
