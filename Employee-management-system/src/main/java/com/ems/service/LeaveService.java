package com.ems.service;

import org.springframework.http.ResponseEntity;

import com.ems.entity.Leave;

public interface LeaveService {

	ResponseEntity<String> applyForLeave(int empId, Leave leave);
}
