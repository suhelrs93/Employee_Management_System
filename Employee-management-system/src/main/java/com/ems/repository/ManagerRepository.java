package com.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ems.entity.Leave;
import com.ems.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{
 
	
}
