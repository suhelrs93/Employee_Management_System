package com.ems.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ems.entity.Leave;
import com.ems.model.LeaveDTO;

@Component
public class LeaveConverter {
	
	//convert from Leave entity to LeaveDTO
		public LeaveDTO convertToLeaveDTO(Leave leave)
		{
			LeaveDTO lDto = new LeaveDTO();
			if(leave!=null)
			{
				BeanUtils.copyProperties(leave, lDto);
			}
			return lDto;
		}
		
		
		//convert from LeaveDTO to Leave entity
		public Leave convertToLeaveEntity(LeaveDTO lDto)
		{
			Leave leave = new Leave();
			if(lDto!=null)
			{
				BeanUtils.copyProperties(lDto, leave);
			}
			return leave;
		}
}
