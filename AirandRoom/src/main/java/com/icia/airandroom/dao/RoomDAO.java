package com.icia.airandroom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.icia.airandroom.dto.MgradeDTO;
import com.icia.airandroom.dto.RoomDTO;
import com.icia.airandroom.dto.RoomGraphDTO;
import com.icia.airandroom.page.Paging;

@Repository
public class RoomDAO {
	
	@Autowired
	private SqlSessionTemplate sql;
	
	public int roomsReg(RoomDTO dto) {
		return sql.insert("Room.roomsReg", dto);
	}

	public void roomPicWrite(RoomDTO dto) {
		sql.insert("Room.roomPicWrite",dto);
	}

	public List<RoomDTO> myRoomsList(String id, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("paging", paging);
		return sql.selectList("Room.myRoomsList", map);
	}

	public int countRoom() {
		return sql.selectOne("Room.countRoom");
	}

	public List<RoomDTO> roomList(Paging paging) {
		return sql.selectList("Room.roomList", paging);
	}

	public int countMyRoom(String id) {
		return sql.selectOne("Room.countMyRoom", id);
	}

	public boolean roomApproval(String rno) {
		int result=sql.update("Room.roomApproval", rno);
		if(result>0) {
			return true;
		}
		return false;
	}

	public boolean roomDeny(String rno) {
		int result=sql.update("Room.roomDeny", rno);
		if(result>0) {
			return true;
		}
		return false;
	}

	public RoomDTO currentInfo(String rno) {
		RoomDTO room = sql.selectOne("Room.currentInfo", rno);
		return room;
	}

	public List<RoomDTO> currentPic(String rno) {
		return sql.selectList("Room.currentPic", rno);
	}

	public List<RoomDTO> selectKeyword(String keyword, String opt) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("opt", opt);
		return sql.selectList("Room.selectKeyword", map);
	}

	public int countSearchRoom(String keyword) {
		return sql.selectOne("Room.countSearchRoom", keyword);
	}

	public List<RoomDTO> searchRoom(String keyword, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("paging", paging);
		return sql.selectList("Room.pagingRoomList", map);
	}
	
	public List<RoomDTO> roomPicList(String rno) {
		return sql.selectList("Room.roomPicList", rno);
	}
	
	public RoomDTO roomUpdateForm(String rno) {
		return sql.selectOne("Room.roomUpdateForm", rno);		
	}

	public int roomsUpdate(RoomDTO room) {
		return sql.insert("Room.roomsUpdate", room);
	}

	public void roomPicDelete(String rimgname) {
		sql.delete("Room.roomPicDelete",rimgname);
	}

	public void roomPicUpdateWrite(RoomDTO room) {
		sql.insert("Room.roomPicUpdateWrite",room);
	}

	public String emailList(String id) {
		return sql.selectOne("Room.emailList", id);
	}

	public String email(String id) {
		return sql.selectOne("Room.email", id);
	}

	public int checkOut(String rbno) {
		return sql.update("Room.checkOut", rbno);
	}

	public int guestEvaluationGrade(MgradeDTO mgrade) {
		return sql.insert("Room.guestEvaluationGrade", mgrade);
	}

	public int guestEvaluation(String rbno) {
		return sql.update("Room.guestEvaluation", rbno);
	}
	
	public int myRoomsDelete(String rno) {
		return sql.delete("Room.myRoomsDelete", rno);
	}

	public List<RoomDTO> searchRoomPic() {
		return sql.selectList("Room.searchRoomPic");
	}

	public String getRpno(String rno) {
		return sql.selectOne("Room.getRpno",rno);
	}

	public RoomDTO searchRoom(String keyword, String rpno) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("rpno", rpno);
		return sql.selectOne("Room.searchRoom",map);
	}

	public int countSearchRoom2(String keyword) {
		return sql.selectOne("Room.countSearchRoom2", keyword);
	}

	public List<RoomDTO> searchRoomRegion(String keyword, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("paging", paging);
		return sql.selectList("Room.pagingRoomListRegion", map);
	}

	public List<RoomDTO> searchRoomNation(String keyword, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("paging", paging);
		return sql.selectList("Room.pagingRoomListNation", map);
	}

	public RoomDTO searchRoomRegion(String keyword, String rpno) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("rpno", rpno);
		return sql.selectOne("Room.searchRoomRegion",map);
	}

	public RoomDTO searchRoomNation(String keyword, String rpno) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("rpno", rpno);
		return sql.selectOne("Room.searchRoomNation",map);
	}

	public int countSearchRoom3() {
		return sql.selectOne("Room.countSearchRoom3");
	}
	
	public List<String> getMonths() {
		return sql.selectList("Room.getMonths");
	}
	public List<Integer> getYear(String id) {
		return sql.selectList("Room.getYear",id);
	}
	
	public List<String> dayList(String month, String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("month", month);
		return sql.selectList("Room.dayList",map);
	}

	public List<String> priceList(String month, String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("month", month);
		return sql.selectList("Room.priceList",map);
	}
	
	public List<RoomGraphDTO> perDayList(String day,String month, String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("day", day);
		map.put("month", month);
		map.put("id", id);
		return sql.selectList("Room.perDayList",map);
	}
	
	public List<String> yearMonth(String year, String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year",year);
		map.put("id", id);
		return sql.selectList("Room.yearMonth",map);
	}

	public List<RoomDTO> mostRoomList(String convertmonth) {
		return sql.selectList("Room.mostRoomList" ,convertmonth);
	}

	public RoomDTO getPic(String rpno) {
		return sql.selectOne("Room.getPic",rpno);
	}

	public String grade(String rno) {
		// TODO Auto-generated method stub
		return sql.selectOne("Room.grade",rno);
	}
}
