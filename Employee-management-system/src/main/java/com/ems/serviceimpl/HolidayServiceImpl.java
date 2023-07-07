package com.ems.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ems.entity.HolidayList;
import com.ems.repository.HolidayRepository;
import com.ems.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService{

	@Autowired
	HolidayRepository holidayRepository;
	
	@Override
	public ResponseEntity<String> saveHoliday(HolidayList holiday) {
		holidayRepository.save(holiday);
		return new ResponseEntity<String>("Holiday details saved successfully.", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> getAllHolidayList() {
		String msg="   Date\t\t\tDay\t\t\tRemark\n";
		List<HolidayList> holidays = holidayRepository.findAll();
		for(HolidayList h : holidays)
		{
			msg += "   "+h.getDate()+"  "+h.getDay()+"  "+h.getRemark()+"\n";
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
