package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.HolidayList;
import com.ems.service.HolidayService;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

	@Autowired
	HolidayService holidayService;
	
	@PostMapping
	public ResponseEntity<String> saveHoliday(@RequestBody HolidayList holiday)
	{
		return holidayService.saveHoliday(holiday);
	}
	
	@GetMapping
	public ResponseEntity<String> getAllHolidays()
	{
		return holidayService.getAllHolidayList();
	}
}
