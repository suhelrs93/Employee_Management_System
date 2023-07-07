package com.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ems.entity.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Integer>{

//	@Query("from leave_app where employee_id=:eid")
//	List<Leave> getLeaveByEmpId(@Param("eid") int empId);
	
	//List<Leave> getByEmployeeId(int eid);
}
