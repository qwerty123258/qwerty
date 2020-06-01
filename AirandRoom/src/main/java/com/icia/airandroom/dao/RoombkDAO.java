package com.icia.airandroom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.airandroom.dto.MgradeDTO;
import com.icia.airandroom.dto.RoombkDTO;
import com.icia.airandroom.page.Paging;

@Repository
public class RoombkDAO {
	
	@Autowired
	private SqlSessionTemplate sql;
	
	
	public int countCustomerBooking(String id) {
		return sql.selectOne("Roombk.countCustomerBooking", id);
	}
	
	public List<RoombkDTO> roomsBookingCheckList(String rno) {
		return sql.selectList("Roombk.roomsBookingCheckList", rno);
	}

	public List<RoombkDTO> selectMyRoombk(String id, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("paging", paging);
		return sql.selectList("Roombk.selectMyRoombk", map);
	}

	
	public int roomBookingCancel(String rno) {
		return sql.delete("Roombk.roomBookingCancel", rno);
	}

	public int countMyRoombk(String id) {
		return sql.selectOne("Roombk.countMyRoombk",id);
	}

	public int roomsBookingDelete(String rbno) {
		return sql.delete("Roombk.roomsBookingDelete", rbno);
	}

	public RoombkDTO roomsBooking(String rbno) {
		return sql.selectOne("Roombk.roomsBooking", rbno);
	}

	public List<RoombkDTO> customerRoomsBookingList(String id, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("paging", paging);
		return sql.selectList("Roombk.customerRoomsBookingList", map);
	}
	
	public void booking(RoombkDTO roombk) {
		sql.insert("Roombk.booking", roombk);
	}
	
	public List<RoombkDTO> checkinSetting(String rno) {
		return sql.selectList("Roombk.checkinSetting", rno);
	}
	public List<RoombkDTO> checkoutSetting(String rno) {
		return sql.selectList("Roombk.checkoutSetting", rno);
	}
	
	public int bookingDelete(String rbno) {
		return sql.delete("Roombk.bookingDelete", rbno);
	}

	public int countGradeView(String id) {
		// TODO Auto-generated method stub
		return sql.selectOne("Roombk.countGradeView",id);
	}

	public List<MgradeDTO> gradeView(String id, Paging paging) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("paging", paging);
		return sql.selectList("Roombk.gradeView", map);
	}

	public String gradeAvg(String id) {
		// TODO Auto-generated method stub
		return sql.selectOne("Roombk.gradeAvg",id);
	}
}
