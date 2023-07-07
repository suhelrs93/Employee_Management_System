package com.ems.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.entity.Department;
import com.ems.entity.Employee;
import com.ems.entity.Leave;
import com.ems.entity.Manager;
import com.ems.entity.Role;
import com.ems.exception.PropertyAlreadyExistsException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.model.LeaveDTO;
import com.ems.model.ManagerDTO;
import com.ems.repository.DepartmentRepository;
import com.ems.repository.LeaveRepository;
import com.ems.repository.ManagerRepository;
import com.ems.repository.RoleRepository;
import com.ems.service.ManagerService;
import com.ems.util.ManagerConverter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j //adding logger using annotation from lombok
public class ManagerServiceImpl implements ManagerService{
	
	//logger statically created
	private static final Logger log = LoggerFactory.getLogger(Manager.class);
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ManagerConverter converter;
	
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Override
	public ManagerDTO addManager(Manager manager) throws PropertyAlreadyExistsException{
		 // checks the managers detail is already present or not
		List<Manager> employees = managerRepository.findAll();
		for(Manager m :employees)
		{
			if(m.getEmail().equals(manager.getEmail()))
			{
				throw new PropertyAlreadyExistsException("Email adready exists.");
			}
			if(m.getContact().equals(manager.getContact()))
			{
				throw new PropertyAlreadyExistsException("Phone number already exists.");
			}
			if(m.getUserName().equals(manager.getUserName()))
			{
				throw new PropertyAlreadyExistsException("User_Name already exists.");
			}
		}
		
		// Set the manager details from the input
		manager.setUserName(manager.getUserName());
		manager.setPassword(manager.getPassword());
		manager.setDesignation("Manager");
		
		// setting role to manager as employee	
		Role role = roleRepository.findById(3).get();
		manager.setRole(role);
				
		// Save the manager details
		managerRepository.save(manager);
		
		//updating log file with date and time
		log.info("New manager details added at "+new Date());
				
		// Convert and return the saved manager as ManagerDTO
		return converter.convertToManagerDTO(manager);
       		
	}

	@Override
	public ManagerDTO assignDeptToManager(int managerId, int deptId) throws ResourceNotFoundException{
		Manager manager = managerRepository.findById(managerId).orElseThrow(() -> 
		new ResourceNotFoundException("Manager", "id", managerId));

        Department department = departmentRepository.findById(deptId).orElseThrow(() -> 
        new ResourceNotFoundException("Department", "id", deptId));

        // Assign  department to the manager
        manager.setDept(department);
        
        department.setTotalEmp(department.getTotalEmp()+1);
        
        //managerRepository.save(manager);
        departmentRepository.save(department);
        
        log.info("Manager with id "+managerId+" is assigned to department id "+deptId+" at "+new Date());
        
        return converter.convertToManagerDTO(manager);
        
	}

	@Override
	public ManagerDTO getManagerById(int id) throws ResourceNotFoundException{
		// Retrieve the manager from the repository based on the provided ID
		Manager manager = managerRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Manager", "Id", id));
				
		//updating log file with date and time
		log.info("Manager with id "+id+" details fetched at "+new Date());
				
		// Convert and return the retrieved manager as ManagerDTO
		return converter.convertToManagerDTO(manager);
	}

	@Override
	public List<ManagerDTO> getAllManagers() {
		List<Manager> managers = managerRepository.findAll();
		
		List<ManagerDTO> managerDTO = new ArrayList<>();
		for(Manager m: managers)
		{
			managerDTO.add(converter.convertToManagerDTO(m));
		}
		
		//updating log file with date and time
		log.info("All manager details fetched at "+new Date());
		
		return managerDTO;
	}

	@Override
	public ManagerDTO updateManager(int id, Manager manager) throws ResourceNotFoundException{
		//we need to check whether Manager exist with id or not in DB
		Manager existManager = managerRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Manager", "Id", id));
			
		List<Manager> managers = managerRepository.findAll();
		for(Manager m :managers)
		{
			if(m.getId()!=id)
			{
				if(m.getEmail().equals(manager.getEmail()))
				{
					throw new PropertyAlreadyExistsException("Email adready exists.");
				}
				if(m.getContact().equals(manager.getContact()))
				{
					throw new PropertyAlreadyExistsException("Phone number already exists.");
				}
				if(m.getUserName().equals(manager.getUserName()))
				{
					throw new PropertyAlreadyExistsException("User_Name already exists.");
				}
			}
		}
		
		//we will get data from the client and set it in existing manager
		existManager.setMName(manager.getMName());
		existManager.setSalary(manager.getSalary());
		existManager.setContact(manager.getContact());
		existManager.setEmail(manager.getEmail());
		existManager.setDesignation("Manager");
		existManager.setDoj(manager.getDoj());
		existManager.setUserName(manager.getUserName());
		existManager.setPassword(manager.getPassword());
				
		// Save the updated manager details
		managerRepository.save(existManager);
				
		//updating log file with date and time
		log.info("Manager with id "+id+" details updated at "+new Date());
				
		// Convert and return the updated Manager as ManagerDTO
		return converter.convertToManagerDTO(existManager);
		 
	}

	@Override
	public String deleteManagerById(int id) throws ResourceNotFoundException{
		String msg=null;
		
		Optional<Manager> opManager = managerRepository.findById(id);
		
		if(opManager.isPresent())
		{
			
			Department d =departmentRepository.findById(opManager.get().getDept().getDeptId()).get();
			if(d!=null)
			{
				d.setTotalEmp(d.getTotalEmp()-1);
			}
	
			managerRepository.deleteById(id);
			departmentRepository.save(d);
			msg = "Record deleted successfully";
			
			//updating log file with date and time
			log.info("Manager with id "+id+" deleted at "+new Date());
		}
		else
		{
			//updating log file with date and time as error
			log.error("Manager with id "+id+ " not found.");
			throw new ResourceNotFoundException("Manager","ID",id);
		}
		return msg;
	}

//	@Override
//	public List<LeaveDTO> getLeavesUnderManager(int managerId) {
//		List<Leave> leaves = leaveRepository.getLeaveByEmpId(eId);
//		return null;
//	}

}
