package com.ems.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ems.entity.Employee;
import com.ems.entity.Leave;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.EmployeeRepository;
import com.ems.repository.LeaveRepository;
import com.ems.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService{

	@Autowired
	LeaveRepository leaveRepository;
	
	@Autowired
	EmployeeRepository empRepository;
	
	@Override
	public ResponseEntity<String> applyForLeave(int empId, Leave leave) throws ResourceNotFoundException{
		Optional<Employee> opEmp =empRepository.findById(empId);
		Employee emp =opEmp.get();
		if(opEmp.isPresent())
		{
			int total = leave.getTo_Date().getDayOfMonth()-leave.getFrom_Date().getDayOfMonth();
			leave.setTotal_Days(total+1);
			
			if(emp.getRemainingLeaves()!=0 && leave.getTotal_Days()<=emp.getRemainingLeaves())
			{
				
				emp.setRemainingLeaves(emp.getRemainingLeaves()-leave.getTotal_Days());
				leave.setEmployee(emp);
//				leave.setStatus("Waiting for Approval");//by default status as Waiting for Approval
				
				empRepository.save(emp);
				leaveRepository.save(leave);
				
			}
			else
			{
				return new ResponseEntity<String>("Your leave balance is over. Remaining Leaves: "+emp.getRemainingLeaves(),
						HttpStatus.BAD_REQUEST);
			}
		}
		else
			throw new ResourceNotFoundException("Employee", "Id", empId);
		return new ResponseEntity<String>("Your leave request has been saved.",HttpStatus.OK);
	}

}
