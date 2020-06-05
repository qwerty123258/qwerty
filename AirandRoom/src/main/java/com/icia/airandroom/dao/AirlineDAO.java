package com.icia.airandroom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.airandroom.dto.AirlineDTO;
import com.icia.airandroom.dto.AirlineGraphDTO;
import com.icia.airandroom.dto.AirlinebkDTO;
import com.icia.airandroom.dto.MemberDTO;
import com.icia.airandroom.dto.ScheduleDTO;
import com.icia.airandroom.page.Paging;

@Repository
public class AirlineDAO {

	@Autowired
	private SqlSessionTemplate sql;

	public List<AirlineDTO> airlineList(Paging paging) {
		List<AirlineDTO> airlineList = sql.selectList("Airline.airlineList", paging);
		return airlineList;
	}

	public int createAirline(AirlineDTO airline) {
		return sql.insert("Airline.createAirline", airline);
	}

	public String checkAname(String aName) {
		return sql.selectOne("Airline.checkAname", aName);
	}

	public List<String> selectStartPoint(String param) {
		return sql.selectList("Airline.selectStartPoint", param);
	}

	public List<String> selectEndPoint(String param) {
		return sql.selectList("Airline.selectEndPoint", param);
	}

	public List<AirlineDTO> selectMyAirline(Paging paging, String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paging", paging);
		map.put("id", id);
		return sql.selectList("Airline.selectMyAirline",map);
	}

	public int deleteAirline(String ano) {
		return sql.delete("Airline.deleteAirline", ano);
	}

	public AirlineDTO modifyAirline(int ano) {
		return sql.selectOne("Airline.modifyAirline", ano);
	}

	public int updateAirline(AirlineDTO airline) {
		return sql.update("Airline.updateAirline", airline);
	}

	public List<AirlineDTO> searchAirline(AirlineDTO airline, Paging paging, String opt) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paging", paging);
		map.put("airline", airline);
		map.put("opt",opt);
		return sql.selectList("Airline.searchAirline", map);
	}

	public boolean airlineApproval(String ano) {
		int result=sql.update("Airline.airlineApproval", ano);
		if(result>0) {
			return true;
		}
		return false;
	}

	public boolean airlineDeny(String ano) {
		int result=sql.update("Airline.airlineDeny", ano);
		if(result>0) {
			return true;
		}
		return false;
	}

	public AirlineDTO currentInfo(String ano) {
		AirlineDTO airline = sql.selectOne("Airline.currentInfo", ano);
		return airline;
	}

	public String currentEmail(String id) {
		return sql.selectOne("Member.currentEmail", id);
	}

	public int countAirlineSearch(AirlineDTO airline, String opt) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("opt", opt);
		map.put("airline", airline);
		return sql.selectOne("Airline.countAirlineSearch", map);
	}
	public void createSeat(AirlineDTO airline) {
		sql.insert("Airline.createSeat", airline);
	}
	public List<AirlineGraphDTO> salesTrend(AirlineGraphDTO airlineGraph) {
		return sql.selectList("Airline.salesTrend", airlineGraph);
	}

	public List<AirlinebkDTO> seatSetting(ScheduleDTO schedule) {
		return sql.selectList("Airlinebk.seatSetting", schedule);
	}

	public void booking(AirlinebkDTO airlinebk) {
		sql.insert("Airlinebk.booking",airlinebk);
		
	}

	public int countAirlineBooking(String id) {
		return sql.selectOne("Airlinebk.countAirlineBooking", id);
	}

	public List<AirlinebkDTO> customerAirlinesBookingList(Paging paging, String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paging", paging);
		map.put("id", id);
 		return sql.selectList("Airlinebk.customerAirlinesBookingList", map);
	}
	public List<AirlinebkDTO> selectAirlinebk(Paging paging) {
		return sql.selectList("Airline.selectAirlinebk",paging);
	}
	
	public int deleteAirlinebk(int ano) {
		return sql.delete("Airline.deleteAirlinebk", ano);
	}

	public List<String> dayList(String month, String id,String year) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("month", month);
		map.put("year", year);
		return sql.selectList("Airline.dayList",map);
	}

	public List<String> priceList(String month, String id,String year) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("month", month);
		map.put("year", year);
		return sql.selectList("Airline.priceList",map);
	}

	public List<Integer> getYear(String id) {
		return sql.selectList("Airline.getYear",id);
	}

	public List<String> yearMonth(String year, String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("year",year);
		map.put("id", id);
		return sql.selectList("Airline.yearMonth",map);
	}
	public List<String> getMonths() {
		return sql.selectList("Airline.getMonths");
	}
	public List<AirlineGraphDTO> perDayList(String day,String month, String id,String year) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("day", day);
		map.put("month", month);
		map.put("id", id);
		map.put("year", year);
		return sql.selectList("Airline.perDayList",map);
	}

	public int countMyAirline(String id) {
		return sql.selectOne("Airline.countMyAirline",id);
	}

	public int countAirline() {
		return sql.selectOne("Airline.countAirline");
	}

	public int countMyAirlinebk(String id) {
		return sql.selectOne("Airline.countMyAirlinebk",id);
	}

	public List<AirlineDTO> selectMyAirlinebk(Paging paging, String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paging", paging);
		map.put("id", id);
		System.out.println(paging.getEndNum());
		System.out.println(paging);
		return sql.selectList("Airline.selectMyAirlinebk",map);
	}

	public boolean bookingCheck(String ano) {
		int count=sql.selectOne("Airlinebk.bookingCheck", ano);
		if(count>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public List<MemberDTO> usersList(String ano) {
		return sql.selectList("Airlinebk.usersList", ano);
	}
	
	public List<AirlineDTO> sortByAirline(AirlineDTO airline, Paging paging, String opt) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paging", paging);
		map.put("airline", airline);
		map.put("opt",opt);
		return sql.selectList("Airline.sortByAirline", map);
	}
	

}
