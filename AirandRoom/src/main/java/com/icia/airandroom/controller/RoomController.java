package com.icia.airandroom.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.icia.airandroom.dto.CommentsDTO;
import com.icia.airandroom.dto.MgradeDTO;
import com.icia.airandroom.dto.RoomDTO;
import com.icia.airandroom.dto.RoomGraphDTO;
import com.icia.airandroom.dto.RoombkDTO;
import com.icia.airandroom.dto.RoomrevDTO;
import com.icia.airandroom.page.Paging;
import com.icia.airandroom.service.RoomService;
import com.icia.airandroom.util.KakaoPay;
import com.icia.airandroom.util.MailSend;

@Controller
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private KakaoPay kakaopay;
	
	@Autowired
	private HttpSession session;

	private ModelAndView mav;

	@ResponseBody
	@RequestMapping(value = "/adminRoomList", method = RequestMethod.GET)
	public Map<String,Object> allRooms(@RequestParam("page") int page) {
		Map<String,Object> map =new HashMap<String,Object>();
		map = roomService.roomList(page);
		return map;
	}
	
	@RequestMapping(value = "/roomManagement", method = RequestMethod.GET)
	public ModelAndView roomManagement() {
		mav=new ModelAndView();
		String id=(String) session.getAttribute("id");
		mav = roomService.yearList(id);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/getRoomDay",method=RequestMethod.GET)
	public List<Integer> roomDay(@RequestParam("month") String month,@RequestParam("year") String year) {
		String id=(String) session.getAttribute("id");
		List<String> roomDay = roomService.roomDay(month,id,year);
		List<Integer> dayList = new ArrayList<Integer>();
		for(int i=0; i<roomDay.size(); i++) {
			dayList.add(Integer.parseInt(roomDay.get(i)));
		}
		return dayList;
	}
	
	@ResponseBody
	@RequestMapping(value="/getRoomPrice",method=RequestMethod.GET)
	public List<Integer> roomPrice(@RequestParam("month") String month,@RequestParam("year") String year) {
		String id=(String) session.getAttribute("id");
		List<String> roomPrice = roomService.roomPrice(month,id,year);
		List<Integer> priceList = new ArrayList<Integer>();
		for(int i=0; i<roomPrice.size(); i++) {
			priceList.add(Integer.parseInt(roomPrice.get(i)));
		}
		return priceList;
	}
	
	@ResponseBody
	@RequestMapping(value="/getRoomMonth",method=RequestMethod.GET)
	public List<String> yearMonth(@RequestParam("year") String year) {
		String id=(String) session.getAttribute("id");
		List<String> yearMonth = roomService.yearMonth(year,id);
		return yearMonth;
	}
	
	@RequestMapping(value = "/roomPerDayList", method = RequestMethod.GET)
	public @ResponseBody List<RoomGraphDTO> perDayList(@RequestParam("day") String day,@RequestParam("month") String month,@RequestParam("year") String year) {
		mav = new ModelAndView();
		String id=(String) session.getAttribute("id");
		List<RoomGraphDTO> graphList = roomService.perDayList(day,month,id,year);
		return graphList;
	}

	@RequestMapping(value = "/roomsRegForm", method = RequestMethod.GET)
	public String roomsRegForm() {
		return "room/RoomsRegForm";
	}

	@ResponseBody
	@RequestMapping(value = "/roomsReg", method = RequestMethod.POST)
	public String roomsReg(@ModelAttribute RoomDTO room, MultipartHttpServletRequest mtfRequest,HttpServletRequest request) throws IOException {
		room.setId((String) session.getAttribute("id"));
		boolean result = roomService.roomReg(room, mtfRequest,request);
		String returns = "no";
		if (result) {
			returns = "yes";
		}
		return returns;

	}

	@RequestMapping(value = "/myRoomsList", method = RequestMethod.GET)
	public ModelAndView myRoomsList(@RequestParam("page") int page) {
		mav = new ModelAndView();
		Paging paging = new Paging();
		String id=(String) session.getAttribute("id");
		int count = roomService.CountmyRoom(id);
		if (page < 0) {
			page = 1;
		}
		paging.setPage(page);
		paging.setTotalCount(count);
		mav = roomService.myRoomsList(id,paging);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/roomApproval", method = RequestMethod.POST)
	public String roomApproval(@RequestParam("rno") String rno) {
		String subject="숙소 등록 신청 결과";
		RoomDTO room=roomService.currentInfo(rno);
		String content=room.getRname()+" 숙소 등록 신청이 승인되었습니다.";
		String email=roomService.currentEmail(room.getId());
		MailSend mailSend = new MailSend();
		mailSend.mailSend(email, subject, content);
		boolean result=roomService.roomApproval(rno);
		if(result) {
			return "Success";
		}
		return "Fail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/roomDeny", method = RequestMethod.POST)
	public String roomDeny(@RequestParam("rno") String rno,HttpServletRequest request) {
		String subject="숙소 등록 신청 결과";
		RoomDTO room=roomService.currentInfo(rno);
		String content=room.getRname()+" 숙소 등록 신청이 거절되었습니다.";
		String email=roomService.currentEmail(room.getId());
		MailSend mailSend = new MailSend();
		mailSend.mailSend(email, subject, content);
		List<RoomDTO> picList =roomService.currentPic(rno);
		for(int i=0; i<picList.size(); i++) {
    		HttpSession session = request.getSession(); 
    		String root_path = session.getServletContext().getRealPath("/");
    		String attach_path = "resources/fileUpload/";
    		String savePath=root_path+""+attach_path+""+picList.get(i).getRimgname();
    		File f = new File(savePath);
    		 if(f.exists()){
    		 f.delete(); 
    		}
		}
		boolean result=roomService.roomDeny(rno);
		if(result) {
			return "Success";
		}
		return "Fail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/selectKeyword", method = RequestMethod.GET)
	public List<RoomDTO> selectKeyword(@RequestParam("keyword") String keyword) {
		List<RoomDTO> keywordList=new ArrayList<RoomDTO>();
		String query[]=keyword.split(" ");
		int count=0;
		int count2=0;
		String opt;
		for(int i=0; i<query.length; i++) {
			if(query[i].equals("한국")) {
				query[i]="대한민국";
			}
			if(query[i].equals("호주")) {
				query[i]="오스트레일리아";
			}
			count=roomService.countSearchRoom(query[i]);
			count2=roomService.countSearchRoom2(query[i]);
			if(count2>count) {
				opt="region";
				keywordList=roomService.selectKeyword(query[i],opt);
			}
			else if(count>count2) {
				opt="nation";
				keywordList=roomService.selectKeyword(query[i],opt);
			
			}
		}
		return keywordList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchRoom", method = RequestMethod.GET)
	public Map<String,Object> searchRoom(@RequestParam("keyword") String keyword,@RequestParam("page") int page) {
		Map<String,Object> map = new HashMap<String,Object>();
		Paging paging = new Paging();
		String query[]=keyword.split(" ");
		int count=0;
		int count2=0;
		int count3=0;
		for(int i=0; i<query.length; i++) {
			if(query[i].equals("한국")) {
				query[i]="대한민국";
			}
			if(query[i].equals("호주")) {
				query[i]="오스트레일리아";
			}
			count=roomService.countSearchRoom(query[i]);
			count2=roomService.countSearchRoom2(query[i]);
			count3=roomService.countSearchRoom3();
			if(count2>count) {
				paging.setPage(page);
				paging.setTotalCount(count2);
				map=roomService.searchRoomRegion(query[i],paging);
			}
			else if(count>count2) {
				paging.setPage(page);
				paging.setTotalCount(count);
				map=roomService.searchRoomNation(query[i],paging);
			}
			else {
				paging.setPage(page);
				paging.setTotalCount(count3);
				map=roomService.searchRoom(query[i],paging);
			}
		}
		return map;
	}
	
	@RequestMapping(value="/addressSearch",method=RequestMethod.GET)
	   public String addressSearch() {
	      return "room/GoogleMap";
	   }
	
	@RequestMapping(value="/roomView",method=RequestMethod.GET)
	public ModelAndView roomView(@RequestParam("rno") String rno,@RequestParam("page") int page) {
		mav=new ModelAndView();	
		mav=roomService.roomView(rno,page);
		return mav;
	}
	@ResponseBody
	@RequestMapping(value="/checkinSetting",method=RequestMethod.GET)
	public List<RoombkDTO> checkinSetting(@RequestParam("rno") String rno) {
		List<RoombkDTO> dateList=roomService.checkinSetting(rno);
		return dateList;
	}
	
	@ResponseBody
	@RequestMapping(value="/checkoutSetting",method=RequestMethod.GET)
	public List<RoombkDTO> checkoutSetting(@RequestParam("rno") String rno) {
		List<RoombkDTO> dateList=roomService.checkoutSetting(rno);
		return dateList;
	}
	
	@RequestMapping(value="/goRoomPay",method=RequestMethod.GET)
	   public ModelAndView goRoomPay(@ModelAttribute RoombkDTO roombk,@RequestParam("roomid") String roomid) {
			mav=new ModelAndView();
			String id= (String) session.getAttribute("id");
			roombk.setId(id);
			mav.addObject("roombk", roombk);
			mav.addObject("roomid", roomid);
			mav.setViewName("room/kakaopay");
	      return mav;
	}
	@RequestMapping(value="/kakaoPay",method=RequestMethod.POST)
	   public String kakaoPay(@ModelAttribute RoombkDTO roombk,@RequestParam("roomid") String roomid) {
			return "redirect:" + kakaopay.kakaoPayReady(roombk,roomid);
	}
	
	@GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token,Model model,@RequestParam("roomid") String roomid,@ModelAttribute RoombkDTO roombk) {
		String id=(String) session.getAttribute("id");
		roombk.setId(id);
		roomService.booking(roombk);
		 model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token,roomid));
    }
	
	@RequestMapping(value="/kakaoPayCancel",method=RequestMethod.GET)
	   public String kakaoPayCancel() {
			return "kakaoPayCancel";
	}
	
	@RequestMapping(value="/kakaoPaySuccessFail",method=RequestMethod.GET)
	   public String kakaoPaySuccessFail() {
			return "kakaoPaySuccessFail";
	}
	
	@RequestMapping(value = "/goSearchRoom", method = RequestMethod.GET)
	public String goSearchRoom() {
		return "room/SearchRoom";
	}

	@ResponseBody
	@RequestMapping(value = "/myRoomsDelete", method = RequestMethod.POST)
	public String myRoomsDelete(@RequestParam("rno") String rno,HttpServletRequest request) {
		boolean bcheck = roomService.roomsBookingCheckList(rno);
		String returns = "no";
		if (bcheck) {
			boolean result = roomService.myRoomsDelete(rno,request);
			if (result) {
				returns = "yes";
			}
			return returns;		
			} else {
			
			returns = "booking";
			return returns;
		}
	}

	

	@RequestMapping(value = "/roomsUpdateForm", method = RequestMethod.GET)
	public ModelAndView roomsUpdateForm(@RequestParam("rno") String rno) {
		mav = new ModelAndView();
		mav = roomService.roomsUpdateForm(rno);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/roomsUpdate", method = RequestMethod.POST) //
	public String roomsUpdate(@ModelAttribute RoomDTO dto, MultipartHttpServletRequest msr,HttpServletRequest request) throws IOException {
		dto.setId((String) session.getAttribute("id"));
		boolean result = roomService.roomsUpdate(dto, msr,request);
		String check = "no";
		if (result) {
			check = "yes";
		}
		return check;
	}

	@ResponseBody
	@RequestMapping(value = "/roomBookingCancel", method = RequestMethod.POST)
	public String roomBookingCancel(@RequestParam("rno") String rno,HttpServletRequest request) {
		
		boolean bcheck = roomService.roomBookingCancel(rno);
		String returns = "no";
		if (bcheck) {
			boolean result = roomService.myRoomsDelete(rno,request);
			if (result) {
				returns = "yes";
			}
	
		}else {		
			returns = "bFail";
			
		}
	
		return returns;	
		}
	   
	@RequestMapping(value="/selectMyRoombk",method=RequestMethod.GET)
	public ModelAndView selectMyRoombk(@RequestParam("page") int page) {
		mav=new ModelAndView();	
		mav=roomService.selectMyRoombk(page);
		return mav;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/roomsBookingDelete", method = RequestMethod.POST) //
	public String roomsBookingDelete(@RequestParam("rbno") String rbno) throws IOException {

		boolean result = roomService.roomsBookingDelete(rbno);
		
		String check = "no";
		if (result) {	
			check = "yes";
		}
		return check;
	}

	
	@ResponseBody
	@RequestMapping(value = "/checkOut", method = RequestMethod.POST) //
	public String checkOut(@RequestParam("rbno") String rbno) throws IOException {

		boolean result = roomService.checkOut(rbno);
		
		String check = "no";
		if (result) {	
			check = "yes";
		}
		return check;
	}
	
	@RequestMapping(value="/guestEvaluationForm" ,method=RequestMethod.GET)
	public ModelAndView guestEvaluationForm(@RequestParam("id") String id,@RequestParam("rbno") String rbno) {
		mav=new ModelAndView();
		mav.addObject("id", id);		
		mav.addObject("rbno", rbno);
		mav.setViewName("room/GuestEvaluationForm");
		return mav;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/guestEvaluation" ,method=RequestMethod.POST)
	public String guestEvaluation(@ModelAttribute MgradeDTO mgrade) {
		boolean result=roomService.guestEvaluation(mgrade);
		String check="no";
		if(result) {
			
			check="yes";
		}
		return check;
	}
	
	@ResponseBody
	@RequestMapping(value="/customerRoomsBookingList",method=RequestMethod.GET)
	public Map<String, Object> customerRoomsBookingList(@RequestParam("page") int page) throws ParseException {
		Map<String,Object> map= new HashMap<String,Object>();
		map=roomService.customerRoomsBookingList(page);
		return map;
	}
	
	
	
	@RequestMapping(value="/reviewWriteForm",method=RequestMethod.GET)
	public ModelAndView reviewWriteForm(@RequestParam("rno") String rno) {
		mav=new ModelAndView();
		mav.addObject("rno", rno);
		mav.setViewName("room/ReviewWriteForm");
		return mav;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/reviewWrite", method = RequestMethod.POST)
	public String reviewWrite(@ModelAttribute RoomrevDTO roomrev, MultipartHttpServletRequest mtfRequest,HttpServletRequest request) throws IOException {
		roomrev.setId((String) session.getAttribute("id"));
		boolean result = roomService.reviewWrite(roomrev, mtfRequest,request);
		
		String returns = "no";
		if (result) {
			returns = "yes";
		}
		return returns;
	}
	
	
	
	@RequestMapping(value="/myReviewListForm",method=RequestMethod.GET)
	public ModelAndView myReviewListForm(@RequestParam("id") String id,@RequestParam("check") String check) {
		mav=new ModelAndView();
		mav.addObject("id", id);
		mav.addObject("check", check);
		mav.setViewName("room/MyReviewListForm");		
		return mav;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/myReviewList",method=RequestMethod.POST)
	public Map<String,Object> myReviewList(@RequestParam("page") int page,@RequestParam("id") String id) {
	
		Map<String,Object> result=roomService.myReviewList(page,id);
		return result;
	}
	
	
	@RequestMapping(value="/reviewUpdateForm",method=RequestMethod.GET)
	public ModelAndView reviewUpdateForm(@RequestParam("revno") String revno) {
		mav=new ModelAndView();
		mav=roomService.reviewUpdateForm(revno);		
		return mav;		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/reviewUpdate",method=RequestMethod.POST)
	public String ReviewUpdate(@ModelAttribute RoomrevDTO roomrev,MultipartHttpServletRequest mtfRequest,HttpServletRequest request) throws IOException {
		boolean result = roomService.reviewUpdate(roomrev, mtfRequest,request);
		
		String returns = "no";
		if (result) {
			returns = "yes";
		}
		return returns;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/reviewDelete", method = RequestMethod.POST)
	public String reviewDelete(@RequestParam("revno") String revno,HttpServletRequest request) {
		String returns = "no";
			boolean result = roomService.reviewDelete(revno,request);
			if (result) {
				returns = "yes";
			}
			return returns;		
			
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/reviewProperty", method = RequestMethod.POST)
	public String reviewProperty(@RequestParam("revno") String revno) {
		String returns = "no";
			boolean result = roomService.reviewProperty(revno);
			if (result) {
				returns = "yes";
			}
			return returns;		
			
	}
	
	@ResponseBody
	@RequestMapping(value = "/reviewCommentList", method = RequestMethod.POST)
	public Map<String, Object> reviewCommentList(@RequestParam("revno") String revno,
			@RequestParam("commentpage") int page) {
		Paging paging = new Paging();
		paging.setPage(page);
		Map<String, Object> map = roomService.reviewCommentList(revno,paging);
		return map;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/reviewCommentWrite", method = RequestMethod.POST)
	public Map<String, Object> reviewCommentWrite(@RequestParam("revno") String revno,
			@RequestParam("contents") String contents, @RequestParam("commentpage") int page) {
		CommentsDTO comments = new CommentsDTO();
		comments.setRevno(revno);
		comments.setContents(contents);
		System.out.println(comments);
		Paging paging = new Paging();
		paging.setPage(page);
		comments.setId((String) session.getAttribute("id"));
		Map<String, Object> list = roomService.reviewCommentWrite(comments, revno,paging);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/reviewCommentDelete", method = RequestMethod.POST)
	public String reviewCommentDelete(@RequestParam("replyno") String replyno) {
	
		String result = roomService.reviewCommentDelete(replyno);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/reviewView", method = RequestMethod.GET)
	public Map<String,Object> reviewView(@RequestParam("revno") String revno,@RequestParam("page") int page) {
		Map<String,Object> map= new HashMap<String,Object>();
		Paging paging=new Paging();
		paging.setPage(page);
		map = roomService.reviewView(revno,paging);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/reviewViewScroll", method = RequestMethod.GET)
	public Map<String,Object> reviewViewScroll(@RequestParam("revno") String revno,@RequestParam("page") int page) {
		Map<String,Object> map= new HashMap<String,Object>();
		Paging paging=new Paging();
		paging.setPage(page);
		map = roomService.reviewViewScroll(revno,paging);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/writeCommentModal", method = RequestMethod.POST)
	public String writeCommentModal(@RequestParam("revno") String revno,@RequestParam("comment") String comment) {
		String id=(String) session.getAttribute("id");
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("revno", revno);
		map.put("comment", comment);
		map.put("id", id);
		boolean result=roomService.writeCommentModal(map);
		if(result) {
			return "Success";
		}
		else {
			return "Fail";
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/bookingDelete", method = RequestMethod.POST)
	public String bookingDelete(@RequestParam("rbno") String rbno) {
		boolean result = roomService.bookingDelete(rbno);
		if (result) {
			return "Success";
		}
		else {
			return "Fail";
		}

	}
	
	
	@RequestMapping(value="/gradeView", method=RequestMethod.GET)
	public ModelAndView gradeView(@RequestParam("id") String id,@RequestParam("page") int page) {
		mav=new ModelAndView();
		mav=roomService.gradeView(id,page);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/mostRoomList", method=RequestMethod.GET)
	public Map<String, Object> mostRoomList(){
		Map<String,Object> map = new HashMap<String,Object>();
		map=roomService.mostRoomList();
		return map;	
	}
	
		@ResponseBody
	@RequestMapping(value="/allReviewList",method=RequestMethod.POST)
	public Map<String,Object> allReviewList(@RequestParam("page") int page,@RequestParam("rno") String rno) {
	
		Map<String,Object> result=roomService.allReviewList(page,rno);
		return result;
	}
		
		@ResponseBody
		@RequestMapping(value="/mostGrade", method=RequestMethod.GET)
		public Map<String, Object> mostGrade(){
			Map<String,Object> map = new HashMap<String,Object>();
			map=roomService.mostGrade();
			return map;	
		}

	
}
