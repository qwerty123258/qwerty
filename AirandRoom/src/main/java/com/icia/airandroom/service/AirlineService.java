package com.icia.airandroom.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.icia.airandroom.dao.AirlineDAO;
import com.icia.airandroom.dto.AirlineDTO;
import com.icia.airandroom.dto.AirlineGraphDTO;
import com.icia.airandroom.dto.AirlinebkDTO;
import com.icia.airandroom.dto.MemberDTO;
import com.icia.airandroom.dto.ScheduleDTO;
import com.icia.airandroom.page.Paging;
import com.icia.airandroom.util.MailSend;


@Service
public class AirlineService {

	private ModelAndView mav;

	@Autowired
	private AirlineDAO adao;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberService memberService;


	public Map<String, Object> airlineList(int page) {
		Map<String,Object> map = new HashMap<String,Object>();
		Paging paging = new Paging();
		int count = adao.countAirline();
		paging.setPage(page);
		paging.setTotalCount(count);
		List<AirlineDTO> airlineList = adao.airlineList(paging);
		map.put("list", airlineList);
		map.put("paging", paging);
		return map;
	}

	public int createAirline(AirlineDTO airline) throws IllegalStateException, IOException {
		int createResult = adao.createAirline(airline);
		return createResult;
	}

	public String checkAname(String aName) {
		String checkResult = adao.checkAname(aName);
		String resultMsg = null;
		if (checkResult == null) {
			resultMsg = "ok";
		} else {
			resultMsg = "no";
		}
		return resultMsg;
	}

	public List<String> selectStartPoint(String param) {
		List<String> resultList = adao.selectStartPoint(param);
		return resultList;
	}

	public List<String> selectEndPoint(String param) {
		List<String> resultList = adao.selectEndPoint(param);
		return resultList;
	}

	public Map<String,Object> selectMyAirline(int page, String id) {
		Map<String,Object> map= new HashMap<String,Object>();
		int count=adao.countMyAirline(id);
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(count);
		List<AirlineDTO> airline = adao.selectMyAirline(paging,id);
		map.put("paging", paging);
		map.put("list", airline);
		return map;
	}

	public boolean deleteAirline(String ano) {
		int deleteResult = adao.deleteAirline(ano);
		if (deleteResult > 0) {
			return true;
		} else {
			return false;
		}
	}

	public ModelAndView modifyAirline(int ano) {
		mav = new ModelAndView();
		AirlineDTO airline = adao.modifyAirline(ano);
		mav.addObject("airline", airline);
		mav.setViewName("airline/ModifyAirline");
		return mav;
	}

	public int updateAirline(AirlineDTO airline) {
		int modifyResult = adao.updateAirline(airline);
		return modifyResult;
	}

	public boolean airlineApproval(String ano) {
		return adao.airlineApproval(ano);
	}

	public boolean airlineDeny(String ano) {
		return adao.airlineDeny(ano);
	}

	public AirlineDTO currentInfo(String ano) {
		AirlineDTO airline=adao.currentInfo(ano);
		return airline;
	}

	public String currentEmail(String id) {
		String email=adao.currentEmail(id);
		return email;
	}
	
	public void createSeat(int srow, int scolumn) {
		for(int i=1; i<=srow; i++) {
			for(int j=1; j<=scolumn; j++) {
				AirlineDTO airline=new AirlineDTO();
				airline.setScolumn(j);
				airline.setSrow(i);
				adao.createSeat(airline);
			}
		}
	}

	public List<AirlinebkDTO> seatSetting(ScheduleDTO schedule) {
		return adao.seatSetting(schedule);
	}

	public void booking(AirlinebkDTO airlinebk) {	
		adao.booking(airlinebk);
	}

	public Map<String,Object> customerAirlinesBookingList(int page) {
		Map<String,Object> map =new HashMap<String,Object>();
		String id=(String) session.getAttribute("id");
		int count=adao.countAirlineBooking(id);
		Paging paging = new Paging();
		paging.setDisplayRow(10);
		paging.setTotalCount(count);
		List<AirlinebkDTO> airlinebkList=adao.customerAirlinesBookingList(paging,id);
		map.put("paging", paging);
		map.put("list", airlinebkList);
		return map;
	}
	
	public boolean deleteAirlinebk(int abno,String id) {
		String email=memberService.usersEmail(id);
		int deleteResult = adao.deleteAirlinebk(abno);
		if (deleteResult > 0) {
			String subject = "안녕하세요 Air&Room 입니다.";
			String content="고객님 예약번호 "+abno+" 번 건의 예약건이 취소되었습니다.";
			MailSend mailSend = new MailSend();
			mailSend.mailSend(email,subject,content);
			return true;
		} 
		else {
			return false;
		}
	}
	
	public List<AirlineGraphDTO> perDayList(String day, String month, String id,String year) {
		mav = new ModelAndView();
		List<AirlineGraphDTO> graphList = adao.perDayList(day,month,id,year);
		return graphList;
	}
	public List<String> yearMonth(String year, String id) {
		List<String> yearMonth = adao.yearMonth(year,id);
		return yearMonth;
	}

	public List<String> airlineDay(String month, String id,String year) {
		List<String> dayList = adao.dayList(month,id,year);
		return dayList;
	}

	public List<String> airlinePrice(String month, String id,String year) {
		List<String> priceList = adao.priceList(month,id,year);
		return priceList;
	}

	public ModelAndView yearList(String id) {
		mav=new ModelAndView();
		List<Integer> yearList = adao.getYear(id);
		mav.addObject("yearList", yearList);
		mav.setViewName("airline/AirlineManagement");
		return mav;
	}

	public Map<String, Object> selectMyAirlinebk(int page, String id) {
		Map<String,Object> map= new HashMap<String,Object>();
		int count=adao.countMyAirlinebk(id);
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(count);
		List<AirlineDTO> airline = adao.selectMyAirlinebk(paging,id);
		map.put("paging", paging);
		map.put("list", airline);
		return map;
	}

	public Map<String, Object> oneway(AirlineDTO airline, int page) {
		Map<String,Object> map = new HashMap<String,Object>();
		String opt="oneway";
		int count=adao.countAirlineSearch(airline,opt);
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(count);
		List<AirlineDTO> list=adao.searchAirline(airline,paging,opt);
		map.put("list", list);
		map.put("paging", paging);
		return map;
	}

	public Map<String, Object> roundtrip(AirlineDTO airline, int page) {
		Map<String,Object> map = new HashMap<String,Object>();
		String opt="roundtrip";
		int count=adao.countAirlineSearch(airline,opt);
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(count);
		List<AirlineDTO> list=adao.searchAirline(airline,paging,opt);
		map.put("list", list);
		map.put("paging", paging);
		return map;
	}

	public boolean bookingCheck(String ano) {
		return adao.bookingCheck(ano);
	}

	public List<MemberDTO> usersList(String ano) {
		return adao.usersList(ano);
	}

	public boolean deleteAirlinebkByUser(int abno) {
		int deleteResult = adao.deleteAirlinebk(abno);
		if(deleteResult>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public Map<String, Object> airlineSortBy(int page, AirlineDTO airline) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(airline.getAirlinetype().matches("가격순정렬편도")) {
			String opt="oneway";
			int count = adao.countAirlineSearch(airline,opt);
			Paging paging = new Paging();
			paging.setPage(page);
			paging.setTotalCount(count);
			List<AirlineDTO> list=adao.sortByAirline(airline,paging,opt);
			for(int i=0; i<list.size(); i++) {
				list.get(i).setAirlinetype("가격순정렬편도");
			}
			map.put("list", list);
			map.put("paging", paging);
		} else if(airline.getAirlinetype().matches("가격순정렬왕복")) {
			String opt="roundtrip";
			int count = adao.countAirlineSearch(airline,opt);
			Paging paging = new Paging();
			paging.setPage(page);
			paging.setTotalCount(count);
			List<AirlineDTO> list=adao.sortByAirline(airline,paging,opt);
			for(int i=0; i<list.size(); i++) {
				list.get(i).setAirlinetype("가격순정렬왕복");
			}
			map.put("list", list);
			map.put("paging", paging);		
		} else if(airline.getAirlinetype().matches("최단시간순편도")) {
			String opt="shortestOneway";
			int count = adao.countAirlineSearch(airline,opt);
			Paging paging = new Paging();
			paging.setPage(page);
			paging.setTotalCount(count);
			List<AirlineDTO> list=adao.sortByAirline(airline,paging,opt);
			for(int i=0; i<list.size(); i++) {
				list.get(i).setAirlinetype("최단시간순편도");
			}
			map.put("list", list);
			map.put("paging", paging);		
		} else if(airline.getAirlinetype().matches("최단시간순왕복")) {
			String opt="shortestRoundtrip";
			int count = adao.countAirlineSearch(airline,opt);
			Paging paging = new Paging();
			paging.setPage(page);
			paging.setTotalCount(count);
			List<AirlineDTO> list=adao.sortByAirline(airline,paging,opt);
			for(int i=0; i<list.size(); i++) {
				list.get(i).setAirlinetype("최단시간순왕복");
			}
			map.put("list", list);
			map.put("paging", paging);		
		} else if(airline.getAirlinetype().matches("최대시간순편도")) {
			String opt="longestOneway";
			int count = adao.countAirlineSearch(airline,opt);
			Paging paging = new Paging();
			paging.setPage(page);
			paging.setTotalCount(count);
			List<AirlineDTO> list=adao.sortByAirline(airline,paging,opt);
			for(int i=0; i<list.size(); i++) {
				list.get(i).setAirlinetype("최대시간순편도");
			}
			map.put("list", list);
			map.put("paging", paging);		
		} else if(airline.getAirlinetype().matches("최대시간순왕복")) {
			String opt="longestRoundtrip";
			int count = adao.countAirlineSearch(airline,opt);
			Paging paging = new Paging();
			paging.setPage(page);
			paging.setTotalCount(count);
			List<AirlineDTO> list=adao.sortByAirline(airline,paging,opt);
			for(int i=0; i<list.size(); i++) {
				list.get(i).setAirlinetype("최대시간순왕복");
			}
			map.put("list", list);
			map.put("paging", paging);		
		}
		return map;
	}
}
