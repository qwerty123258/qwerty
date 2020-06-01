package com.icia.airandroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.airandroom.dao.ScheduleDAO;
import com.icia.airandroom.dto.ScheduleDTO;

@Service
public class ScheduleService {
	
	private ModelAndView mav;
	
	@Autowired
	private ScheduleDAO sdao;

	public ModelAndView selectSchedule(String ano) {
		mav=new ModelAndView();
		List<ScheduleDTO> scheduleList=sdao.selectSchedule(ano);
		mav.addObject("scheduleList",scheduleList);
		mav.addObject("ano", ano);
		mav.setViewName("airline/ScheduleManagement");
		return mav;
	}

	public boolean scheduleAdd(ScheduleDTO schedule) {
		boolean result=sdao.scheduleAdd(schedule);
		if(result) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean checkSchedule(ScheduleDTO schedule) {
		boolean result=sdao.checkSchedule(schedule);
		if(result) {
			return true;
		}
		return false;
	}

	public List<ScheduleDTO> timeSetting(String ano) {
		
		return sdao.timeSetting(ano);
	}

	public boolean scheduleDelete(String scno) {
		return sdao.scheduleDelete(scno);
	}

	public boolean scheduleUpdate(ScheduleDTO schedule) {
		return sdao.scheduleUpdate(schedule);
	}
}
