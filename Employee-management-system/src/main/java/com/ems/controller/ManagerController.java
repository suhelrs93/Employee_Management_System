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
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Manager;
import com.ems.model.ManagerDTO;
import com.ems.service.ManagerService;
import com.ems.util.ManagerConverter;

@RestController
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;

	@Autowired
	private ManagerConverter converter;
	
	@PostMapping("/api/manager")
	public ManagerDTO saveManager(@Valid @RequestBody ManagerDTO mdto)
	{
		Manager manager = converter.convertToManagerEntity(mdto);
		return managerService.addManager(manager);
	}
	
	@GetMapping("/manager/{managerId}")
	public ManagerDTO getManagerById(@PathVariable("managerId") int id)
	{
		return managerService.getManagerById(id);		
	}

	@GetMapping("/manager")
	public List<ManagerDTO> getAllManagers()
	{
		return managerService.getAllManagers();
	}
	
	
	@PutMapping("/manager/{managerId}")
	public ManagerDTO updateManager(@PathVariable("managerId") int id,
			@Valid @RequestBody ManagerDTO mdto)
	{
		Manager m = converter.convertToManagerEntity(mdto);
		return managerService.updateManager(id, m);
	}
	
	@PostMapping("/api/assignManager/{manId}/assignToDept/{deptId}")
	public ManagerDTO assignMngToDept(@PathVariable("manId") int manId,
			@PathVariable("deptId") int deptId)
	{
		// Call the assignDeptToManager method of the ManagerService to assign an manager to a department
		return managerService.assignDeptToManager(manId, deptId);
	}
	
	@DeleteMapping("/api/manager/{managerId}")
	public ResponseEntity<String> deleteManagerById(@PathVariable("managerId") int id)
	{
		return new ResponseEntity<String>(managerService.deleteManagerById(id),
				HttpStatus.OK);
	}
}
