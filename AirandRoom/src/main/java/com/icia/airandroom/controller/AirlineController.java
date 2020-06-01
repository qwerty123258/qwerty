package com.icia.airandroom.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.icia.airandroom.dto.AirlineDTO;
import com.icia.airandroom.dto.AirlineGraphDTO;
import com.icia.airandroom.dto.AirlinebkDTO;
import com.icia.airandroom.dto.MemberDTO;
import com.icia.airandroom.dto.ScheduleDTO;
import com.icia.airandroom.service.AirlineService;
import com.icia.airandroom.service.ScheduleService;
import com.icia.airandroom.util.KakaoPay;
import com.icia.airandroom.util.MailSend;

@Controller
public class AirlineController {

	@Autowired
	private AirlineService airlineService;
	
	@Autowired
	private ScheduleService scheduleService;

	private ModelAndView mav;
	 
	@Autowired
	private HttpSession session;
	
	@Autowired
	private KakaoPay kakaopay;

	@ResponseBody
	@RequestMapping(value = "/adminAirlineList", method = RequestMethod.GET)
	public Map<String,Object> allLines(@RequestParam("page") int page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map = airlineService.airlineList(page);
		return map;
	}

	@RequestMapping(value = "/goCreateAirline", method = RequestMethod.GET)
	public String goCreateAirline() {
		return "airline/CreateAirline";
	}

	@RequestMapping(value = "/airlineManagement", method = RequestMethod.GET)
	public ModelAndView airlineManagement() {
		mav=new ModelAndView();
		String id=(String) session.getAttribute("id");
		mav = airlineService.yearList(id);
		return mav;
	}

	@RequestMapping(value = "/goSearchAirline", method = RequestMethod.GET)
	public String goSearchAirline() {
		return "airline/SearchAirline";
	}

	@ResponseBody
	@RequestMapping(value = "/checkAname", method = RequestMethod.POST)
	public String goMemberDetail(@RequestParam("aName") String aName) {
		String resultMsg = airlineService.checkAname(aName);
		return resultMsg;

	}
	@ResponseBody
	@RequestMapping(value = "/registerAirline", method = RequestMethod.POST)
	public int createAirline(@ModelAttribute AirlineDTO airline) throws IOException {
		mav = new ModelAndView();
		int min = airline.getMinute();
		int hour = airline.getHour();
		String result = hour*60 + min+"분";
		airline.setTakentime(result);
		airline.setId((String)session.getAttribute("id"));
		int createResult = airlineService.createAirline(airline);
		if(createResult>0) {
			int srow=airline.getSrow();
			int scolumn=airline.getScolumn();
			airlineService.createSeat(srow,scolumn);
		}
		return createResult;
	}

	@ResponseBody
	@RequestMapping(value = "/selectStartPoint", method = RequestMethod.GET)
	public List<String> selectStartPoint(@RequestParam("param") String param) {
		List<String> resultList = airlineService.selectStartPoint(param);
		return resultList;
	}

	@ResponseBody
	@RequestMapping(value = "/selectEndPoint", method = RequestMethod.GET)
	public List<String> selectEndPoint(@RequestParam("param") String param) {
		List<String> resultList = airlineService.selectEndPoint(param);
		return resultList;
	}
	
	

	@RequestMapping(value = "/selectAirline", method = RequestMethod.GET)
	public String selectAirline() {

		return "airline/SelectAirline";
	}
	
	@ResponseBody
	@RequestMapping(value = "/selectMyAirline", method = RequestMethod.GET)
	public Map<String,Object> SelectMyAirline(@RequestParam("page") int page) {
		String id=(String) session.getAttribute("id");
		Map<String,Object> map = new HashMap<String,Object>();
		map = airlineService.selectMyAirline(page,id);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/selectMyAirlinebk", method = RequestMethod.GET)
	public Map<String,Object> SelectMyAirlinebk(@RequestParam("page") int page) {
		String id=(String) session.getAttribute("id");
		Map<String,Object> map = new HashMap<String,Object>();
		map = airlineService.selectMyAirlinebk(page,id);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteAirline", method = RequestMethod.GET)
	public String deleteAirline(@RequestParam("ano") String ano) {
		boolean boookingCheck=airlineService.bookingCheck(ano);
		if(boookingCheck) {
			return "Booking";
		}
		else {
			boolean delResult=airlineService.deleteAirline(ano);
			if(delResult) {
				return "Success";
			}
			else {
				return "Fail";
			}
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteAirlineWithBooking", method = RequestMethod.GET)
	public String deleteAirlineWithBooking(@RequestParam("ano") String ano,@RequestParam("aname") String aname) {
			List<MemberDTO> member = airlineService.usersList(ano);
			for(int i=0; i<member.size(); i++) {
				MailSend mailSend = new MailSend();
				String subject = "안녕하세요 Air&Room 입니다.";
				String content="고객님이 예약하신 "+aname+" 의 예약건이 취소되었습니다.";
				mailSend.mailSend(member.get(i).getEmail(), subject, content);
			}
			boolean delResult=airlineService.deleteAirline(ano);
			if(delResult) {
				return "Success";
			}
			else {
				return "Fail";
			}
	}

	@RequestMapping(value = "/modifyAirline", method = RequestMethod.GET)
	public ModelAndView modifyAirline(@RequestParam("ano") int ano) {
		mav = new ModelAndView();
		mav = airlineService.modifyAirline(ano);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/updateAirline", method = RequestMethod.POST)
	public int updateAirline(@ModelAttribute AirlineDTO airline) {
		int modifyResult = airlineService.updateAirline(airline);
		return modifyResult;
	}
	
	@ResponseBody
	@RequestMapping(value = "/oneway", method = RequestMethod.GET)
	public Map<String,Object> oneway(@ModelAttribute AirlineDTO airline,@RequestParam("page") int page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map=airlineService.oneway(airline,page);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/roundtrip", method = RequestMethod.GET)
	public Map<String,Object> roundtrip(@ModelAttribute AirlineDTO airline,@RequestParam("page") int page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map=airlineService.roundtrip(airline,page);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/airlineApproval", method = RequestMethod.POST)
	public String roomApproval(@RequestParam("ano") String ano) {
		String subject="노선 등록 신청 결과";
		AirlineDTO airline=airlineService.currentInfo(ano);
		String content=airline.getAname()+" 노선 등록 신청이 승인되었습니다.";
		String email=airlineService.currentEmail(airline.getId());
		MailSend mailSend = new MailSend();
		mailSend.mailSend(email, subject, content);
		boolean result=airlineService.airlineApproval(ano);
		if(result) {
			return "Success";
		}
		return "Fail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/airlineDeny", method = RequestMethod.POST)
	public String roomDeny(@RequestParam("ano") String ano) {
		String subject="노선 등록 신청 결과";
		AirlineDTO airline=airlineService.currentInfo(ano);
		String content=airline.getAname()+" 노선 등록 신청이 거절되었습니다.";
		String email=airlineService.currentEmail(airline.getId());
		MailSend mailSend = new MailSend();
		mailSend.mailSend(email, subject, content);
		boolean result=airlineService.airlineDeny(ano);
		if(result) {
			return "Success";
		}
		return "Fail";
	}
	
	
	@RequestMapping(value = "/scheduleManagement", method = RequestMethod.GET)
	public ModelAndView scheduleManagement(@RequestParam("ano") String ano) {
		mav=new ModelAndView();
		mav=scheduleService.selectSchedule(ano);
		return mav;
	}
	
	@RequestMapping(value = "/goScheduleAdd", method = RequestMethod.GET)
	public ModelAndView goScheduleAdd(@RequestParam("ano") String ano) {
		mav=new ModelAndView();
		mav.addObject("ano", ano);
		mav.setViewName("airline/ScheduleAdd");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/scheduleAdd", method = RequestMethod.GET)
	public String scheduleAdd(@ModelAttribute ScheduleDTO schedule) {
		boolean result=scheduleService.checkSchedule(schedule);
		if(result) {
			return "Overlap";
		}
		else {
			boolean inputResult=scheduleService.scheduleAdd(schedule);
			if(inputResult) {
				return "Success";
			}
			else {
				return "Fail";
			}
		}
	}
	
	@RequestMapping(value = "/timeSetting", method = RequestMethod.GET)
	public @ResponseBody List<ScheduleDTO> timeSetting(@RequestParam("ano") String ano) {
		List<ScheduleDTO> scheduleList = scheduleService.timeSetting(ano);
		return scheduleList;
	}
	
	@RequestMapping(value = "/seatSetting", method = RequestMethod.GET)
	public @ResponseBody List<AirlinebkDTO> seatSetting(@ModelAttribute ScheduleDTO schedule) {
		List<AirlinebkDTO> seatList = airlineService.seatSetting(schedule);
		return seatList;
	}
	
	@RequestMapping(value="/goAirlinePay",method=RequestMethod.GET)
	   public ModelAndView goAirlinePay(@ModelAttribute AirlinebkDTO airlinebk,@RequestParam("airlineid") String airlineid) {
			mav=new ModelAndView();
			String id= (String) session.getAttribute("id");
			airlinebk.setId(id);
			mav.addObject("airlinebk", airlinebk);
			mav.addObject("airlineid", airlineid);
			mav.setViewName("airline/kakaopay");
	      return mav;
	}
	@RequestMapping(value="/airlinekakaoPay",method=RequestMethod.POST)
	   public String kakaoPay(@ModelAttribute AirlinebkDTO airlinebk,@RequestParam("airlineid") String airlineid) {
			session.setAttribute("seats", airlinebk.getSeats());
			return "redirect:" + kakaopay.kakaoPayReady(airlinebk,airlineid);
	}
	@RequestMapping(value="/AirlineKakaoPaySuccess")
		public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token,Model model,@RequestParam("airlineid") String airlineid,@ModelAttribute AirlinebkDTO airlinebk) {
		String id=(String) session.getAttribute("id");
		airlinebk.setId(id);
		String[] srow=null;
		String scolumn[]=null;
		String[] seats=(String[]) session.getAttribute("seats");
		for(int i=0; i<seats.length; i++) {
			srow=seats[i].split("행");
			scolumn=srow[1].split("열");
			airlinebk.setSrow(srow[0]);
			airlinebk.setScolumn(scolumn[0]);
			int aprice=Integer.parseInt(airlinebk.getPrice())/seats.length;
			String price=Integer.toString(aprice);
			airlinebk.setAprice(price);
			airlineService.booking(airlinebk);
		}
		session.removeAttribute("seats");
		model.addAttribute("airlinebk",airlinebk);
		model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token,airlineid));
 }
	@ResponseBody
	@RequestMapping(value="/scheduleDelete",method=RequestMethod.GET)
	public String scheduleDelete(@RequestParam("scno") String scno) {
		boolean result=scheduleService.scheduleDelete(scno);
		if(result) {
			return "Success";
		}
		return "Fail";
	}
	
	@ResponseBody
	@RequestMapping(value="/scheduleUpdate",method=RequestMethod.GET)
	public String scheduleUpdate(@ModelAttribute ScheduleDTO schedule) {
			boolean inputResult=scheduleService.scheduleUpdate(schedule);
			if(inputResult) {
				return "Success";
			}
			else {
				return "Fail";
			}
}
	
	@ResponseBody
	@RequestMapping(value="/customerAirlinesBookingList",method=RequestMethod.GET)
	public Map<String,Object> customerAirlinesBookingList(@RequestParam("page") int page) {
		Map<String,Object> map= new HashMap<String,Object>();
		map=airlineService.customerAirlinesBookingList(page);
		return map;
		}
	
	@ResponseBody
	@RequestMapping(value = "/deleteAirlinebk", method = RequestMethod.GET)
	public String deleteAirlinebk(@RequestParam("abno") int abno,@RequestParam("id") String id) {
		
		boolean result = airlineService.deleteAirlinebk(abno,id);
		if(result) {
			return "Success";
		}
		else {
			return "Fail";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteAirlinebkByUser", method = RequestMethod.GET)
	public String deleteAirlinebkByUser(@RequestParam("abno") int abno) {
		boolean result = airlineService.deleteAirlinebkByUser(abno);
		if(result) {
			return "Success";
		}
		else {
			return "Fail";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getAirlineMonth",method=RequestMethod.GET)
	public List<String> yearMonth(@RequestParam("year") String year) {
		String id=(String) session.getAttribute("id");
		List<String> yearMonth = airlineService.yearMonth(year,id);
		return yearMonth;
	}
	
	@ResponseBody
	@RequestMapping(value="/getAirlineDay",method=RequestMethod.GET)
	public List<Integer> airlineDay(@RequestParam("month") String month) {
		String id=(String) session.getAttribute("id");
		List<String> airlineDay = airlineService.airlineDay(month,id);
		List<Integer> dayList = new ArrayList<Integer>();
		for(int i=0; i<airlineDay.size(); i++) {
			dayList.add(Integer.parseInt(airlineDay.get(i)));
		}
		return dayList;
	}
	
	@ResponseBody
	@RequestMapping(value="/getAirlinePrice",method=RequestMethod.GET)
	public List<Integer> airlinePrice(@RequestParam("month") String month) {
		String id=(String) session.getAttribute("id");
		List<String> airlinePrice = airlineService.airlinePrice(month,id);
		List<Integer> priceList = new ArrayList<Integer>();
		for(int i=0; i<airlinePrice.size(); i++) {
			priceList.add(Integer.parseInt(airlinePrice.get(i)));
		}
		return priceList;
	}
	
	@RequestMapping(value = "/airlinePerDayList", method = RequestMethod.GET)
	public @ResponseBody List<AirlineGraphDTO> perDayList(@RequestParam("day") String day,@RequestParam("month") String month) {
		String id=(String) session.getAttribute("id");
		mav = new ModelAndView();
		List<AirlineGraphDTO> graphList = airlineService.perDayList(day,month,id);
		return graphList;
	}
	
	@ResponseBody
	@RequestMapping(value="/airlineSortBy",method=RequestMethod.GET)
	public Map<String,Object> airlineSortBy(@RequestParam("page") int page, @ModelAttribute AirlineDTO airline) {
		Map<String,Object> map = new HashMap<String,Object>();
		map = airlineService.airlineSortBy(page,airline);
		return map;
	}
}
