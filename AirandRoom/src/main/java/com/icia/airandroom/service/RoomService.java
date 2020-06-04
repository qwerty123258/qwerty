package com.icia.airandroom.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.icia.airandroom.dao.MemberDAO;
import com.icia.airandroom.dao.RoomDAO;
import com.icia.airandroom.dao.RoombkDAO;
import com.icia.airandroom.dao.RoomrevDAO;
import com.icia.airandroom.dto.CommentsDTO;
import com.icia.airandroom.dto.MgradeDTO;
import com.icia.airandroom.dto.RoomDTO;
import com.icia.airandroom.dto.RoomGraphDTO;
import com.icia.airandroom.dto.RoombkDTO;
import com.icia.airandroom.dto.RoomrevDTO;
import com.icia.airandroom.page.Paging;
import com.icia.airandroom.util.MailSend;

@Service
public class RoomService {

	@Autowired
	private RoomDAO rdao;

	@Autowired
	private RoombkDAO rbdao;

	@Autowired
	private RoomrevDAO revdao;

	@Autowired
	private MemberDAO mdao;

	@Autowired
	private HttpSession session;

	private ModelAndView mav;

	public Map<String,Object> roomList(int page) {
		Map<String,Object> map = new HashMap<String,Object>();
		Paging paging = new Paging();
		int count = rdao.countRoom();
		paging.setPage(page);
		paging.setTotalCount(count);
		List<RoomDTO> roomList = rdao.roomList(paging);
		map.put("list", roomList);
		map.put("paging", paging);
		return map;
	}

	public boolean roomReg(RoomDTO room, MultipartHttpServletRequest mtfRequest, HttpServletRequest request) throws IOException {
		List<MultipartFile> filelist = mtfRequest.getFiles("pic");
		int result = rdao.roomsReg(room);
		// MultipartHttpServletRequest타입변수를 가져와 업로드할 파일의 정보를가져옴
		// 정보가담긴mtfRequest변수에담긴 파일이아닌 파일들의 정보를 가져와야하므로 getFiles("여러파일의 정보가담긴 파일태그
		// name")을 사용
		if (result > 0) {
			if (filelist.get(0).getSize() != 0) {// 파일리스트에 저장된값이 있으면 반응하게함
				for (int i = 0; i < filelist.size(); i++) {// filelist에 담긴 파일들의 갯수(size)만큼for문을 돌림
					room.setRimgoriname(filelist.get(i).getOriginalFilename());// filelist에담긴파일중.i번째인 파일의정보를get해서 파일의
																				// 오리지널
																				// 이름만.getOriginalFilename()으로 가져와서
					// 업로드할 파일이 중복되면 덮어쓰기되므로 중복안되게 파일명을 랜덤하게 하기위한 메소드에 세팅하여 저장할값을 리턴받는다
					String uploadFileName = uploadFile(filelist.get(i).getOriginalFilename(),
							filelist.get(i).getBytes(),request);
					room.setRimgname(uploadFileName);// 나중에 jsp에서 띄울때 필요한 저장된 파일의 이름 저장
					rdao.roomPicWrite(room);

				}
			}
			return true;
		} else {

		}
		return false;

	}

	private String uploadFile(String originalName, byte[] bytes, HttpServletRequest request) throws IOException {
		// 랜덤한 파일명을 만들기위한 랜덤값을 주는 uuid객체생성
		HttpSession session = request.getSession(); 
		String root_path = session.getServletContext().getRealPath("/");
		String attach_path = "resources/fileUpload/";
		UUID uuid = UUID.randomUUID();
		// 랜덤생성이름+파일이름 합치기
		String saveFileName = uuid + "_" + originalName;
		String savePath = root_path+""+attach_path;// 저장경로
		File target = new File(savePath, saveFileName);// 파일경로+랜덤이름과합쳐진 파일이름이담긴 파일로 새로 만들어 변수에대입
		FileCopyUtils.copy(bytes, target);// target와 가져온 바이트를 세팅하여 새로운 파일로 만들어(copy) 디렉토리로복사
		return saveFileName; // 파일경로+랜덤이름과합쳐진 파일이름이담긴변수 리턴
	}

	public ModelAndView myRoomsList(String id, Paging paging) {
		mav=new ModelAndView();
		List<RoomDTO> list = rdao.myRoomsList(id,paging);
		mav.addObject("roomList", list);
		mav.addObject("paging", paging);
		mav.setViewName("room/MyRoomsList");
		return mav;

	}

	public int CountmyRoom(String id) {
		return rdao.countMyRoom(id);
	}

	public boolean roomApproval(String rno) {
		return rdao.roomApproval(rno);
	}

	public boolean roomDeny(String rno) {
		return rdao.roomDeny(rno);
	}

	public RoomDTO currentInfo(String rno) {
		RoomDTO room=rdao.currentInfo(rno);
		return room;
	}

	public String currentEmail(String id) {
		String email=mdao.currentEmail(id);
		return email;
	}

	public List<RoomDTO> currentPic(String rno) {
		List<RoomDTO> picList = rdao.currentPic(rno);
		return picList;
	}

	public List<RoomDTO> selectKeyword(String keyword, String opt) {
		List<RoomDTO> keywordList = rdao.selectKeyword(keyword,opt);
		List<RoomDTO> keywordList2=new ArrayList<RoomDTO>();
		for(int i=0; i<keywordList.size(); i++) {
			RoomDTO room = new RoomDTO();
			room.setSearchoutput(opt);
			room.setNation(keywordList.get(i).getNation());
			room.setRegion(keywordList.get(i).getRegion());
			keywordList2.add(room);
		}
		return keywordList2;
	}

	public Map<String, Object> searchRoom(String keyword, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<RoomDTO> pagingRoomList = rdao.searchRoom(keyword,paging);
		List<RoomDTO> roomList = new ArrayList<RoomDTO>();
		for(int i=0; i<pagingRoomList.size(); i++) {
			RoomDTO room = new RoomDTO();
			String rno= pagingRoomList.get(i).getRno();
			String rpno=rdao.getRpno(rno);
			room=rdao.searchRoom(keyword,rpno);
			roomList.add(room);
		}
		map.put("roomList", roomList);
		map.put("keyword",keyword);
		map.put("paging", paging);
		map.put("output", "전 세계 여러 곳");
		return map;
	}

	public int countSearchRoom(String keyword) {
		return rdao.countSearchRoom(keyword);
	}
	
	public ModelAndView roomView(String rno,int page) {
		mav=new ModelAndView();
		Paging paging = new Paging();		
		int count=revdao.countRev(rno);	
		if(page<=0) {
			page=1;
		}
		paging.setPage(page);
		paging.setTotalCount(count);
		List<RoomDTO> plist=rdao.roomPicList(rno);
		RoomDTO room=rdao.roomUpdateForm(rno);
		List<RoomrevDTO> revlist=revdao.reviewList(rno,paging);
		String grade=rdao.grade(rno);
		mav.addObject("grade", grade);
		mav.addObject("plist", plist);		
		mav.addObject("paging", paging);	
		mav.addObject("room", room);
		mav.addObject("revList", revlist);
		mav.setViewName("room/RoomView");
		return mav;
	}

	public List<RoombkDTO> checkinSetting(String rno) {
		List<RoombkDTO> dateList = rbdao.checkinSetting(rno);
		return dateList;
	}
	
	public List<RoombkDTO> checkoutSetting(String rno) {
		List<RoombkDTO> dateList = rbdao.checkoutSetting(rno);
		return dateList;
	}
	
	public boolean myRoomsDelete(String rno,HttpServletRequest request) {
		List<RoomDTO> list = rdao.roomPicList(rno);
		HttpSession session = request.getSession(); 
		String root_path = session.getServletContext().getRealPath("/");
		String attach_path = "resources/fileUpload/";
		for (int s = 0; s < list.size(); s++) {// 삭제파일이 담긴 배열의 length만큼 for문 돌림
			String savePath = root_path+""+attach_path+""
					+ list.get(s).getRimgname();// 저장경로
			File f = new File(savePath); // 파일타입으로 만들기위해 파일을 선언하여 삭제할파일 세팅한다
			if (f.exists()) {// 경로에 해당파일 존재시 반응
				f.delete(); // 파일삭제
			}
		}
		int result = rdao.myRoomsDelete(rno);
		if (result > 0) {
			return true;
		}
		return false;

	}

	public ModelAndView roomsUpdateForm(String rno) {
		RoomDTO room = rdao.roomUpdateForm(rno);
		List<RoomDTO> list = rdao.roomPicList(rno);
		mav.addObject("room", room);
		mav.addObject("roomPic", list);
		mav.setViewName("room/RoomUpdateForm");
		return mav;

	}

	public boolean roomsUpdate(RoomDTO room, MultipartHttpServletRequest mtfRequest,HttpServletRequest request) throws IOException {
		List<MultipartFile> filelist = mtfRequest.getFiles("pic");
		String[] array = mtfRequest.getParameterValues("deleteFiles");

		mav = new ModelAndView();
		int result = rdao.roomsUpdate(room);
		if (result > 0) {
			// MultipartHttpServletRequest타입변수를 가져와 업로드할 파일의 정보를가져옴
			// 정보가담긴mtfRequest변수에담긴 파일이아닌 파일들의 정보를 가져와야하므로 getFiles("여러파일의 정보가담긴 파일태그
			// name")을 사용
			if (filelist.get(0).getSize() != 0) {// 파일리스트에 저장된값이 있으면 파일사이즈가 있으니 0보다크니까 반응하게함
				for (int i = 0; i < filelist.size(); i++) {// filelist에 담긴 파일들의 갯수(size)만큼for문을 돌림
					room.setRimgoriname(filelist.get(i).getOriginalFilename());// filelist에담긴파일중.i번째인 파일의정보를get해서 파일의
																				// 오리지널
																				// 이름만.getOriginalFilename()으로 가져와서
					// 업로드할 파일이 중복되면 덮어쓰기되므로 중복안되게 파일명을 랜덤하게 하기위한 메소드에 세팅하여 저장할값을 리턴받는다
					String uploadFileName = uploadFile(filelist.get(i).getOriginalFilename(),
							filelist.get(i).getBytes(),request);
					room.setRimgname(uploadFileName);// 나중에 jsp에서 띄울때 필요한 저장된 파일의 이름 저장
					rdao.roomPicUpdateWrite(room);

				}
				if (mtfRequest.getParameterValues("deleteFiles") != null) {// 삭제할파일이 담겨있을경우 반응
					HttpSession session = request.getSession(); 
					String root_path = session.getServletContext().getRealPath("/");
					String attach_path = "resources/fileUpload/";
					for (int s = 0; s < array.length; s++) {// 삭제파일이 담긴 배열의 length만큼 for문 돌림
						rdao.roomPicDelete(array[s]); // db에서삭제
						String savePath = root_path+""+attach_path+""
								+ array[s];// 저장경로
						File f = new File(savePath); // 파일타입으로 만들기위해 파일을 선언하여 삭제할파일 세팅한다
						if (f.exists()) {// 경로에 해당파일 존재시 반응
							f.delete(); // 파일삭제

						}
					}

				} else {

				}
				return true;

			} else {
				if (mtfRequest.getParameterValues("deleteFiles") != null) {// 삭제할파일이 담겨있을경우 반응
					HttpSession session = request.getSession(); 
					String root_path = session.getServletContext().getRealPath("/");
					String attach_path = "resources/fileUpload/";
					for (int s = 0; s < array.length; s++) {// 삭제파일이 담긴 배열의 length만큼 for문 돌림
						rdao.roomPicDelete(array[s]); // db에서삭제
						String savePath = root_path+""+attach_path+""
								+ array[s];// 저장경로
						File f = new File(savePath); // 파일타입으로 만들기위해 파일을 선언하여 삭제할파일 세팅한다
						if (f.exists()) {// 경로에 해당파일 존재시 반응
							f.delete(); // 파일삭제
						}
					}

				} else {

				}
			}

			return true;

		}
		return false;
	}

	public boolean roomsBookingCheckList(String rno) {
		List<RoombkDTO> result = rbdao.roomsBookingCheckList(rno);
		if (result.size() == 0) {
			return true;
		}
		return false;

	}

	public boolean roomBookingCancel(String rno) {
		List<RoombkDTO> rlist = rbdao.roomsBookingCheckList(rno);
		for (int i = 0; i < rlist.size(); i++) {
			String email = rdao.emailList(rlist.get(i).getId());
			String subject = "Air & Room 예약취소";
			String content = "해당  " + rlist.get(i).getRname() + " 예약이 숙소내 사정으로 인하여 취소 되었습니다.";
			MailSend mailSend=new MailSend();
			mailSend.mailSend(email, subject, content);
		}
		int result = rbdao.roomBookingCancel(rno);
		if (result > 0) {
			return true;
		}
		return false;

	}

	public ModelAndView selectMyRoombk(int page) {
		mav = new ModelAndView();
		Paging paging = new Paging();
		String id=(String) session.getAttribute("id");
		int count = rbdao.countMyRoombk(id);
		if (page <= 0) {
			page = 1;
		}
		paging.setPage(page);
		paging.setTotalCount(count);
		List<RoombkDTO> list = rbdao.selectMyRoombk(id,paging);
		mav.addObject("list", list);
		mav.addObject("paging", paging);
		mav.setViewName("room/RoomsBookingList");
		return mav;
	}

	public boolean roomsBookingDelete(String rbno) {
		RoombkDTO roombk = rbdao.roomsBooking(rbno);
		int result = rbdao.roomsBookingDelete(rbno);
		if (result > 0) {
			String email = rdao.email(roombk.getId());
			String subject = "Air & Room 예약취소";
			String content = "해당  " + roombk.getRname() + " 예약이 숙소내 사정으로 인하여 취소 되었습니다.";
			MailSend mailSend=new MailSend();
			mailSend.mailSend(email, subject, content);
			return true;
		}
		return false;

	}

	public boolean checkOut(String rbno) {
		int result = rdao.checkOut(rbno);
		if (result > 0) {
			return true;
		}
		return false;
	}

	public boolean guestEvaluation(MgradeDTO mgrade) {
		int result = rdao.guestEvaluationGrade(mgrade);
		if (result > 0) {
			rdao.guestEvaluation(mgrade.getRbno());
			return true;
		}
		return false;
	}

	public Map<String, Object> customerRoomsBookingList(int page) throws ParseException {
		Map<String,Object> map = new HashMap<String,Object>();
		Paging paging = new Paging();
		String id = (String) session.getAttribute("id");
		int count = rbdao.countCustomerBooking(id);
		if (page <= 0) {
			page = 1;
		}
		paging.setPage(page);
		paging.setTotalCount(count);
		Calendar cal = Calendar.getInstance();
		 
		//현재 년도, 월, 일
		int year = cal.get ( cal.YEAR );
		int month = cal.get ( cal.MONTH ) + 1 ;
		int date = cal.get ( cal.DATE ) ;
		String convertMonth;
		String convertDay;
        if(month<10) {
        	convertMonth="0"+month;
        }
        else {
        	convertMonth=""+month;
        }
        if(date<10) {
        	convertDay="0"+date;
        }
        else {
        	convertDay=""+date;
        }
		String convertDate=year+"-"+convertMonth+"-"+convertDay;
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, year);
        calendar1.set(Calendar.MONTH, month-1);
        calendar1.set(Calendar.DAY_OF_MONTH, date);
       
		List<RoombkDTO> list = rbdao.customerRoomsBookingList(id,paging);
		List<RoombkDTO> convertList=new ArrayList<RoombkDTO>();
        //현재날짜가 체크인 날짜(-7)보다 크면 1, 작으면 -1, 같으면 0
        int result1;
       // 체크인 날짜(-7)가 현재 날짜보다 크면 1 작으면 -1 같으면 0
        int result2;
        //현재 날짜가 체크아웃 날짜보다 크면 1 작으면 -1 같으면 0
        int result3;
        //체크아웃 날짜가 현재 날짜보다 크면 1 작으면 -1 같으면 0
        int result4;
		for(int i=0; i<list.size(); i++) {
			RoombkDTO roombk=new RoombkDTO();
			//체크인 데이터 얻기
			String year2=list.get(i).getCheckindate().split("-")[0];
			String month2=list.get(i).getCheckindate().split("-")[1];
			String day2=list.get(i).getCheckindate().split("-")[2];
			//체크아웃 데이터 얻기
			String year3=list.get(i).getCheckoutdate().split("-")[0];
			String month3=list.get(i).getCheckoutdate().split("-")[1];
			String day3=list.get(i).getCheckoutdate().split("-")[2];
			
			//체크인 날짜
	        Calendar calendar2 = Calendar.getInstance();
	        calendar2.set(Calendar.YEAR, Integer.parseInt(year2));
	        calendar2.set(Calendar.MONTH, Integer.parseInt(month2)-1);
	        calendar2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day2)-7);
	        
	        //체크아웃 날짜
	        Calendar calendar3 = Calendar.getInstance();
	        calendar3.set(Calendar.YEAR, Integer.parseInt(year3));
	        calendar3.set(Calendar.MONTH, Integer.parseInt(month3)-1);
	        calendar3.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day3));
	        
	        
	        //현재날짜가 체크인 날짜(-7)보다 크면 1, 작으면 -1, 같으면 0
	       result1 = calendar1.compareTo(calendar2);
	       // 체크인 날짜(-7)가 현재 날짜보다 크면 1 작으면 -1 같으면 0
	        result2 = calendar2.compareTo(calendar1);
	        //현재 날짜가 체크아웃 날짜보다 크면 1 작으면 -1 같으면 0
	        result3 = calendar1.compareTo(calendar3);
	        //체크아웃 날짜가 현재 날짜보다 크면 1 작으면 -1 같으면 0
	        result4 = calendar3.compareTo(calendar1);
		    if(result3==1 && result1==1) {
		        	roombk.setCancle("취소 불가");
		        	roombk.setCheckout("체크아웃 가능");
		    }
		    else if(result3==-1 && result1==-1) {
	        	roombk.setCancle("취소 가능");
	        	roombk.setCheckout("체크아웃 불가");
	        }
		    else if(result2==-1 && result4==1) {
	        	roombk.setCancle("취소 불가");
	        	roombk.setCheckout("체크아웃 불가");
			    if(convertDate.equals(list.get(i).getCheckoutdate())) {
		        	roombk.setCheckout("체크아웃 가능");
			    }
	        }
	        roombk.setRbno(list.get(i).getRbno());
	        roombk.setRno(list.get(i).getRno());
	        roombk.setRname(list.get(i).getRname());
	        roombk.setId(list.get(i).getId());
	        roombk.setCheckindate(list.get(i).getCheckindate());
	        roombk.setCheckoutdate(list.get(i).getCheckoutdate());
	        roombk.setBookingdate(list.get(i).getBookingdate());
	        roombk.setPrice(list.get(i).getPrice());
	        roombk.setChecks(list.get(i).getChecks());
	        roombk.setGrade(list.get(i).getGrade());
			convertList.add(roombk);
		}
		map.put("list", convertList);
		map.put("paging", paging);
		return map;
	}

	public boolean reviewWrite(RoomrevDTO roomrev, MultipartHttpServletRequest mtfRequest,HttpServletRequest request) throws IOException {
		List<MultipartFile> filelist = mtfRequest.getFiles("pic");
		int result = revdao.reviewWrite(roomrev);
		revdao.grade(roomrev);
		// MultipartHttpServletRequest타입변수를 가져와 업로드할 파일의 정보를가져옴
		// 정보가담긴mtfRequest변수에담긴 파일이아닌 파일들의 정보를 가져와야하므로 getFiles("여러파일의 정보가담긴 파일태그
		// name")을 사용
		if (result > 0) {
			if (filelist.get(0).getSize() != 0) {// 파일리스트에 저장된값이 있으면 반응하게함
				for (int i = 0; i < filelist.size(); i++) {// filelist에 담긴 파일들의 갯수(size)만큼for문을 돌림
					roomrev.setRimgoriname(filelist.get(i).getOriginalFilename());// filelist에담긴파일중.i번째인 파일의정보를get해서 파일의
																					// 오리지널
																					// 이름만.getOriginalFilename()으로 가져와서
					// 업로드할 파일이 중복되면 덮어쓰기되므로 중복안되게 파일명을 랜덤하게 하기위한 메소드에 세팅하여 저장할값을 리턴받는다
					String uploadFileName = uploadFile(filelist.get(i).getOriginalFilename(),
							filelist.get(i).getBytes(),request);
					roomrev.setRimgname(uploadFileName);// 나중에 jsp에서 띄울때 필요한 저장된 파일의 이름 저장
					revdao.roomrevPicWrite(roomrev);

				}
			}
			return true;
		} else {

		}
		return false;
	}

	public Map<String, Object> myReviewList(int page,String nickname) {
		Paging paging = new Paging();
		String id = nickname;
		int count = revdao.myReviewListCount(id);
		if (page <= 0) {
			page = 1;
		}
		paging.setPage(page);
		paging.setTotalCount(count);
		Map<String, Object> result = new HashMap<String, Object>();
		List<RoomrevDTO> list = revdao.myReviewList(id, paging);
		for (int i = 0; i < list.size(); i++) {
			List<RoomrevDTO> rimgname = revdao.revpicGet(list.get(i).getRevno());
			if(rimgname!=null) {
				list.get(i).setRimgname(rimgname.get(0).getRimgname());							
			}
		}
		result.put("list", list);
		result.put("paging", paging);
		return result;

	}

	public ModelAndView reviewUpdateForm(String revno) {
		mav=new ModelAndView();
		RoomrevDTO roomrev=revdao.reviewUpdateForm(revno);
		List<RoomrevDTO> revpic=revdao.revpicList(revno);
		mav.addObject("roomrev", roomrev);
		mav.addObject("plist",revpic );
		mav.setViewName("room/ReviewUpdateForm");
		return mav;
	}

	public boolean reviewUpdate(RoomrevDTO roomrev, MultipartHttpServletRequest mtfRequest,HttpServletRequest request) throws IOException {
		List<MultipartFile> filelist = mtfRequest.getFiles("pic");
		String[] array = mtfRequest.getParameterValues("deleteFiles");

		mav = new ModelAndView();
		int result = revdao.reviewUpdate(roomrev);
		if (result > 0) {
			// MultipartHttpServletRequest타입변수를 가져와 업로드할 파일의 정보를가져옴
			// 정보가담긴mtfRequest변수에담긴 파일이아닌 파일들의 정보를 가져와야하므로 getFiles("여러파일의 정보가담긴 파일태그
			// name")을 사용
			if (filelist.get(0).getSize() != 0) {// 파일리스트에 저장된값이 있으면 파일사이즈가 있으니 0보다크니까 반응하게함
				for (int i = 0; i < filelist.size(); i++) {// filelist에 담긴 파일들의 갯수(size)만큼for문을 돌림
					roomrev.setRimgoriname(filelist.get(i).getOriginalFilename());// filelist에담긴파일중.i번째인 파일의정보를get해서 파일의
																				// 오리지널
																				// 이름만.getOriginalFilename()으로 가져와서
					// 업로드할 파일이 중복되면 덮어쓰기되므로 중복안되게 파일명을 랜덤하게 하기위한 메소드에 세팅하여 저장할값을 리턴받는다
					String uploadFileName = uploadFile(filelist.get(i).getOriginalFilename(),
							filelist.get(i).getBytes(),request);
					roomrev.setRimgname(uploadFileName);// 나중에 jsp에서 띄울때 필요한 저장된 파일의 이름 저장
					revdao.revpicUpdateWrite(roomrev);

				}
				if (mtfRequest.getParameterValues("deleteFiles") != null) {// 삭제할파일이 담겨있을경우 반응
					HttpSession session = request.getSession(); 
					String root_path = session.getServletContext().getRealPath("/");
					String attach_path = "resources/fileUpload/";
					for (int s = 0; s < array.length; s++) {// 삭제파일이 담긴 배열의 length만큼 for문 돌림
						revdao.revpicDelete(array[s]); // db에서삭제
						String savePath = root_path+""+attach_path+""
								+ array[s];// 저장경로
						File f = new File(savePath); // 파일타입으로 만들기위해 파일을 선언하여 삭제할파일 세팅한다
						if (f.exists()) {// 경로에 해당파일 존재시 반응
							f.delete(); // 파일삭제

						}
					}

				} else {

				}
				return true;

			} else {
				if (mtfRequest.getParameterValues("deleteFiles") != null) {// 삭제할파일이 담겨있을경우 반응
					HttpSession session = request.getSession(); 
					String root_path = session.getServletContext().getRealPath("/");
					String attach_path = "resources/fileUpload/";
					for (int s = 0; s < array.length; s++) {// 삭제파일이 담긴 배열의 length만큼 for문 돌림
						revdao.revpicDelete(array[s]); // db에서삭제
						String savePath = root_path+""+attach_path+""
								+ array[s];// 저장경로
						File f = new File(savePath); // 파일타입으로 만들기위해 파일을 선언하여 삭제할파일 세팅한다
						if (f.exists()) {// 경로에 해당파일 존재시 반응
							f.delete(); // 파일삭제
						}
					}

				} else {

				}
			}

			return true;

		}
		return false;

	}

	public boolean reviewDelete(String revno,HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		String root_path = session.getServletContext().getRealPath("/");
		String attach_path = "resources/fileUpload/";
		List<RoomrevDTO> list = revdao.revpicList(revno);
		for (int s = 0; s < list.size(); s++) {// 삭제파일이 담긴 배열의 length만큼 for문 돌림
			String savePath = root_path+""+attach_path+""
					+ list.get(s).getRimgname();// 저장경로
			File f = new File(savePath); // 파일타입으로 만들기위해 파일을 선언하여 삭제할파일 세팅한다
			if (f.exists()) {// 경로에 해당파일 존재시 반응
				f.delete(); // 파일삭제
			}
		}
		int result = revdao.reviewDelete(revno);
		if (result > 0) {
			return true;
		}
		return false;

	}

	public boolean reviewProperty(String revno) {
		String rlike=revdao.reviewPropertySelect(revno);
		RoomrevDTO roomrev=new RoomrevDTO();
		roomrev.setRevno(revno);
		if(rlike.equals("Y")) {
			rlike="N";
		}else {
			rlike="Y";
		}
		roomrev.setRlike(rlike);
		int result=revdao.reviewProperty(roomrev);
		if(result>0) {
			return true;
		}
		return false;
	}

	public void booking(RoombkDTO roombk) {
		rbdao.booking(roombk);
		
	}
	
	public Map<String, Object> reviewCommentList(String revno, Paging paging) {
		int count = revdao.countComment(revno);
		Map<String, Object> map = new HashMap<String, Object>();
		paging.setTotalCount(count);
		String opt="최신순";
		List<CommentsDTO> list = revdao.reviewCommentListPaging(paging,revno,opt);
		map.put("list", list);
		map.put("paging", paging);
		return map;
	}

	public Map<String, Object> reviewCommentWrite(CommentsDTO comments, String revno, Paging paging) {
		revdao.reviewCommentWrite(comments);
		int count = revdao.countComment(revno);
		paging.setTotalCount(count);
		Map<String, Object> map = new HashMap<String, Object>();
		String opt="최신순";
		List<CommentsDTO> list = revdao.reviewCommentListPaging(paging,revno,opt);
		map.put("list", list);
		map.put("paging", paging);
		return map;
	}

	public String reviewCommentDelete(String replyno) {
		int result=revdao.reviewCommentDelete(replyno);
	if(result>0) {
	return "Success";	
	}else {
		return "Fail";	
	}	
	}

	public Map<String, Object> reviewView(String revno, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		RoomrevDTO roomrev = revdao.reviewView(revno);
		int count = revdao.countComment(revno);
		paging.setTotalCount(count);
		String opt="작성시간순";
		List<CommentsDTO> comments = revdao.reviewCommentListPaging(paging,revno,opt);
		List<RoomrevDTO> plist = revdao.revpicList(revno);
		map.put("roomrev", roomrev);
		map.put("plist", plist);
		map.put("comment", comments);
		map.put("paging", paging);
		return map;
	}
	
	public Map<String, Object> reviewViewScroll(String revno, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		int count = revdao.countComment(revno);
		paging.setTotalCount(count);
		String opt="작성시간순";
		List<CommentsDTO> comments = revdao.reviewCommentListPaging(paging,revno,opt);
		map.put("comment", comments);
		map.put("paging", paging);
		return map;
	}
	
	public boolean bookingDelete(String rbno) {
		int result=rbdao.bookingDelete(rbno);
		if(result>0) {
			return true;
			
		}
		return false;
	}

	public int countSearchRoom2(String keyword) {
		return rdao.countSearchRoom2(keyword);
	}

	public Map<String, Object> searchRoomRegion(String keyword, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<RoomDTO> pagingRoomListRegion = rdao.searchRoomRegion(keyword,paging);
		List<RoomDTO> roomList = new ArrayList<RoomDTO>();
		for(int i=0; i<pagingRoomListRegion.size(); i++) {
			RoomDTO room = new RoomDTO();
			String rno= pagingRoomListRegion.get(i).getRno();
			String rpno=rdao.getRpno(rno);
			room=rdao.searchRoomRegion(keyword,rpno);
			roomList.add(room);
		}
		map.put("roomList", roomList);
		map.put("keyword",keyword);
		map.put("paging", paging);
		map.put("output", roomList.get(0).getRegion());
		return map;
	}

	public Map<String, Object> searchRoomNation(String keyword, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<RoomDTO> pagingRoomListNation = rdao.searchRoomNation(keyword,paging);
		List<RoomDTO> roomList = new ArrayList<RoomDTO>();
		for(int i=0; i<pagingRoomListNation.size(); i++) {
			RoomDTO room = new RoomDTO();
			String rno= pagingRoomListNation.get(i).getRno();
			String rpno=rdao.getRpno(rno);
			room=rdao.searchRoomNation(keyword,rpno);
			roomList.add(room);
		}
		map.put("roomList", roomList);
		map.put("keyword",keyword);
		map.put("paging", paging);
		map.put("output", roomList.get(0).getNation());
		return map;
	}

	public int countSearchRoom3() {
		return rdao.countSearchRoom3();
	}
	
		public ModelAndView gradeView(String id, int page) {
		mav = new ModelAndView();
		Paging paging = new Paging();
		int count = rbdao.countGradeView(id);
		if (page <= 0) {
			page = 1;
		}
		paging.setPage(page);
		paging.setTotalCount(count);
		List<MgradeDTO> list=rbdao.gradeView(id,paging);
		String grade=rbdao.gradeAvg(id);
		mav.addObject("list", list);
		mav.addObject("paging", paging);
		mav.addObject("grade", grade);
		mav.addObject("id", id);
		mav.setViewName("room/GradeView");
		return mav;
	}
		
		public List<RoomGraphDTO> perDayList(String day, String month, String id, String year) {
			mav = new ModelAndView();
			List<RoomGraphDTO> graphList = rdao.perDayList(day,month,id,year);
			return graphList;
		}
		public List<String> yearMonth(String year, String id) {
			List<String> yearMonth = rdao.yearMonth(year,id);
			return yearMonth;
		}

		public List<String> roomDay(String month, String id, String year) {
			List<String> dayList = rdao.dayList(month,id,year);
			return dayList;
		}

		public List<String> roomPrice(String month, String id, String year) {
			List<String> priceList = rdao.priceList(month,id,year);
			return priceList;
		}

		public ModelAndView yearList(String id) {
			mav=new ModelAndView();
			List<Integer> yearList = rdao.getYear(id);
			mav.addObject("yearList", yearList);
			mav.setViewName("room/RoomManagement");
			return mav;
		}

		public boolean writeCommentModal(Map<String, Object> map) {	
			return revdao.writeCommentModal(map);
		}

		public Map<String, Object> mostRoomList() {
			List<RoomDTO> list=rdao.mostRoomList();
			List<RoomDTO> piclist=new ArrayList<RoomDTO>();
			for(int i=0; i<list.size(); i++) {
				RoomDTO room = new RoomDTO();
				String rno= list.get(i).getRno();
				String rpno=rdao.getRpno(rno);
				room=rdao.getPic(rpno);
				piclist.add(room);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", piclist);
			return map;
		}
		
		public Map<String, Object> allReviewList(int page, String roomrno) {
			Paging paging = new Paging();
			String rno = roomrno;
			int count = revdao.allReviewListCount(rno);
			if (page <= 0) {
				page = 1;
			}
			paging.setPage(page);
			paging.setTotalCount(count);
			Map<String, Object> result = new HashMap<String, Object>();
			List<RoomrevDTO> list = revdao.allReviewList(rno, paging);
			for (int i = 0; i < list.size(); i++) {
				List<RoomrevDTO> rimgname = revdao.revpicGet(list.get(i).getRevno());
				if(rimgname!=null) {
					list.get(i).setRimgname(rimgname.get(0).getRimgname());							
				}
			}
			result.put("list", list);
			result.put("paging", paging);
			return result;
		}

				public Map<String, Object> mostGrade() {
					List<RoomDTO> list=rdao.mostGrade();
					List<RoomDTO> piclist=new ArrayList<RoomDTO>();
					for(int i=0; i<list.size(); i++) {
						RoomDTO room = new RoomDTO();
						String rno= list.get(i).getRno();
						String rpno=rdao.getRpno(rno);
						room=rdao.getPic2(rpno);
						piclist.add(room);
					}
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("list", piclist);
					return map;
				}

}
