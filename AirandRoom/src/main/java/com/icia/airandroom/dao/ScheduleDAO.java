package com.icia.airandroom.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.airandroom.dto.ScheduleDTO;

@Repository
public class ScheduleDAO {
	@Autowired
	private SqlSessionTemplate sql;
	
	public List<ScheduleDTO> selectSchedule(String ano) {
		return sql.selectList("Schedule.selectSchedule", ano);
	}

	public boolean scheduleAdd(ScheduleDTO schedule) {
		int result=sql.insert("Schedule.scheduleAdd", schedule);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean checkSchedule(ScheduleDTO schedule) {
		int result=sql.selectOne("Schedule.checkSchedule", schedule);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}
	public List<ScheduleDTO> timeSetting(String ano) {
		return sql.selectList("Schedule.timeSetting", ano);
	}

	public boolean scheduleDelete(String scno) {
		int result=sql.delete("Schedule.scheduleDelete", scno);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	
	}

	public boolean scheduleUpdate(ScheduleDTO schedule) {
		int result=sql.update("Schedule.scheduleUpdate", schedule);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}
}
