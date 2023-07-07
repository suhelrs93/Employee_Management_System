package com.ems.service;

import org.springframework.http.ResponseEntity;

import com.ems.entity.HolidayList;

public interface HolidayService {

	//method to save new holidays
	ResponseEntity<String> saveHoliday(HolidayList holiday);
	
	//method to get all holiday list
	ResponseEntity<String> getAllHolidayList();
}
