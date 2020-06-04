package com.icia.airandroom.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.icia.airandroom.dto.MemberDTO;
import com.icia.airandroom.page.Paging;
import com.icia.airandroom.service.CommunityService;
import com.icia.airandroom.service.KakaoService;
import com.icia.airandroom.service.MemberService;
import com.icia.airandroom.util.KakaoJoinApi;
import com.icia.airandroom.util.MailSend;




@Controller
public class MemberController {
	
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private KakaoService kakaoService;
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "home";
	}
	
	
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public ModelAndView memberList(@RequestParam("page") int page) {
		mav=new ModelAndView();
		Paging paging = new Paging();
		int count=memberService.countMember();
		if(page<0) {
			page=1;
		}
        paging.setPage(page);
        paging.setTotalCount(count);
		mav=memberService.memberList(paging);
		return mav;
	}
	
	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPage() {
		return "member/MyPage";
	}
	
	
	@RequestMapping(value="/loginMembers", method=RequestMethod.GET)
	public String loginMembers() {
		return "member/Login";
	}
	
	@RequestMapping(value="/checkDelMembers", method=RequestMethod.GET)
	public String checkDelMembers() {
		return "member/CheckDelMember";
	}
	
	@RequestMapping(value="/checkMembers", method=RequestMethod.GET)
	public String checkMembers() {
		return "member/CheckMember";
	}
	
	@RequestMapping(value="/modifyMembers", method=RequestMethod.GET)
	public ModelAndView modifyMembers() {
		mav=new ModelAndView();
		String id = (String) session.getAttribute("id");
		mav=memberService.modifyMembers(id);
		return mav;
	}
	
	@RequestMapping(value="/emailModify", method=RequestMethod.GET)
	public String emailModify() {
		return "member/EmailModify";
	}

	@ResponseBody
	@RequestMapping(value="/checkMember", method=RequestMethod.POST)
	public String checkMember(@ModelAttribute MemberDTO member) {
		String id=(String) session.getAttribute("id");
		member.setId(id);
		boolean result=memberService.login(member);
		if(result) {
			return "Success";
		}
		return "Fail";
	}
	
	@ResponseBody
	@RequestMapping(value="/checkDelMember", method=RequestMethod.POST)
	public String checkDelMember(@ModelAttribute MemberDTO member) {
		String id=(String) session.getAttribute("id");
		member.setId(id);
		boolean result=memberService.login(member);
		if(result) {
			return "Success";
		}
		return "Fail";
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteMember", method=RequestMethod.GET)
	public String deleteMember(HttpServletRequest request) {
		String id=(String) session.getAttribute("id");
		if(id.equals("admin")) { 
			return "Admin";
		}
		boolean result=memberService.deleteMember(id,request);
		if(result) {
			session.invalidate();
			return "Success";
		}
		else {
			return "Fail";
		}
	}
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute MemberDTO member,HttpServletResponse response) {
		boolean result=memberService.login(member);
		if(result) {
			boolean period=memberService.checkperiod(member);
			if(period) {
				return "report";
			}
			else {
					memberService.searchKind(member);
					session.setAttribute("kind", member.getKind());
					session.setAttribute("id", member.getId());			
					if(member.isCheckAutoLogin()) {
						Cookie cookie =new Cookie("loginCookie", session.getId());
						cookie.setPath("/");
						int amount=60*60*24*1;
						cookie.setMaxAge(amount);
						response.addCookie(cookie);
						Date sessionLimit =new Date(System.currentTimeMillis() + (1000*amount));
						memberService.keepLogin(member.getId(), session.getId(), sessionLimit);   				
						return "Success";	
					}
					else {
						return "Success";
					}
			}
		}
		else {
			return "Fail";
		}
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletResponse response,HttpServletRequest request) {
        Object obj = session.getAttribute("id");
        if ( obj !=null ){
            String member = (String)obj;
            // null이 아닐 경우 제거
            session.invalidate();// 세션 전체를 날려버림
            //쿠키를 가져와보고
            Cookie loginCookie = WebUtils.getCookie(request,"loginCookie");
            if ( loginCookie !=null ){
                // null이 아니면 존재하면!
                loginCookie.setPath("/");
                // 쿠키는 없앨 때 유효시간을 0으로 설정하는 것 !!! invalidate같은거 없음.
                loginCookie.setMaxAge(0);
                // 쿠키 설정을 적용한다.
                response.addCookie(loginCookie);
                // 사용자 테이블에서도 유효기간을 현재시간으로 다시 세팅해줘야함.
                Date date =new Date(System.currentTimeMillis());
                memberService.keepLogin(member, session.getId(), date);
            }
        }
		return "redirect:/main";
	}
	
	@RequestMapping(value="/goSearch", method=RequestMethod.GET)
	public String goSearch() {
			return "member/SearchID";
	}
	
	@ResponseBody
	@RequestMapping(value="/searchID", method=RequestMethod.POST)
	public String searchID(@ModelAttribute MemberDTO member) {
		boolean result=memberService.searchID(member);
		if(result) {
			return member.getId();
		}
		else {
			return "Fail";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/searchPW", method=RequestMethod.POST)
	public String searchPW(@ModelAttribute MemberDTO member) {
		boolean result=memberService.searchPW(member);
		if(result) {
			String subject="Air & Room 비밀번호 발송";
			String content="비밀번호 : " + member.getPw();
			MailSend mailSend = new MailSend();
			mailSend.mailSend(member.getEmail(),subject,content);
			return "Success";
		}
		else {
			return "Fail";
		}
	}
	
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String welcome() {
		return "member/Welcome";
	}
	
	@RequestMapping(value="/createMembers", method=RequestMethod.GET)
	public ModelAndView createMembers(@RequestParam("kind") String kind) {
		mav=new ModelAndView();
		mav.addObject("kind", kind);
		mav.setViewName("member/JoinForm");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/checkID", method=RequestMethod.GET)
	public String checkID(@RequestParam("id") String id) {
		boolean result=memberService.checkID(id);
		if(result) {
			return "Overlap";
		}
		return "NotOverlap";
	}
	
	@ResponseBody
	@RequestMapping(value="/checkEmail", method=RequestMethod.POST)
	public String checkEmail(@RequestParam("email") String email) {
		boolean result=memberService.checkEmail(email);
		if(result) {
			return "Overlap";
		}
		return "NotOverlap";
	}
	
	
	@RequestMapping(value="/sendCode", method=RequestMethod.GET)
	public ModelAndView sendCode(@RequestParam("email") String email) {
		mav=new ModelAndView();
		String code=codeGen();
		String subject="Air & Room 이메일 인증 코드 발송";
		String content="인증코드 : " + code;
		MailSend mailSend = new MailSend();
		mailSend.mailSend(email,subject,content);
		session.setAttribute("code", code);
		mav.addObject("email", email);
		mav.setViewName("member/EmailCertify");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/reSendCode", method=RequestMethod.GET)
	public String reSendCode(@RequestParam("email") String email) {
		String code=codeGen();
		String subject="Air & Room 이메일 인증 코드 발송";
		String content="인증코드 : " + code;
		MailSend mailSend = new MailSend();
		mailSend.mailSend(email,subject,content);
		session.invalidate();
		session.setAttribute("code", code);
		return "Success";
	}
	
	@ResponseBody
	@RequestMapping(value="/emailCertify", method=RequestMethod.GET)
	public String emailCertify(@RequestParam("code") String code) {
		String sendCode=(String) session.getAttribute("code");
		if(sendCode.equals(code)) {
			return "Success";
		}
		return "Fail";
	}
	
	@ResponseBody
	@RequestMapping(value="/emailUpdate", method=RequestMethod.POST)
	public String emailCertify(@ModelAttribute MemberDTO member) {
		String id=(String) session.getAttribute("id");
		member.setId(id);
		boolean result=memberService.emailUpdate(member);
		if(result) {
			return "Success";
		}
		return "Fail";
	}
	
    public String codeGen() {
        Random random = new Random();
        String code = "";
        for(int i=0;i<4;i++) {
            String randomCode = Integer.toString(random.nextInt(10));
                if(!code.contains(randomCode)) {
                    code += randomCode;
                }
                else {
                    i-=1;
                }
            }
        return code;
    }
    
    @ResponseBody
    @RequestMapping(value="/createMember", method=RequestMethod.POST)
    public String createMember(@ModelAttribute MemberDTO member,MultipartHttpServletRequest mtfRequest,HttpServletRequest request) throws IOException {
    	String imgoriname=mtfRequest.getFile("pic").getOriginalFilename();
    	String imgname=upload(imgoriname,mtfRequest.getFile("pic").getBytes(),request);
    	member.setImgname(imgname);
    	member.setImgoriname(imgoriname);
    	boolean result=memberService.createMember(member);
    	if(result) {
    		return "Success";
    	}
		return "Fail";
    }
    
    @ResponseBody
    @RequestMapping(value="/updateMember", method=RequestMethod.POST)
    public String updateMember(@ModelAttribute MemberDTO member,MultipartHttpServletRequest mtfRequest,HttpServletRequest request) throws IOException {
    	String id=(String)session.getAttribute("id");
    	member.setId(id);
    	String beforeimgname=member.getImgname();
    	if(!mtfRequest.getFile("pic").isEmpty()) {
        	String imgoriname=mtfRequest.getFile("pic").getOriginalFilename();
        	String imgname=upload(imgoriname,mtfRequest.getFile("pic").getBytes(), request);
        	member.setImgname(imgname);
        	member.setImgoriname(imgoriname);
        	boolean result=memberService.updateMember(member);
        	if(result) {
        		HttpSession session = request.getSession(); 
        		String root_path = session.getServletContext().getRealPath("/");
        		String attach_path = "resources/fileUpload/";
        		String savePath=root_path+""+attach_path+""+beforeimgname;
	    		File f = new File(savePath);
	    		 if(f.exists()){
	    		 f.delete(); 
	    		}
        		return "Success";
        	}
        	else {
        		return "Fail";
        	}
    	}
    	else {
        	boolean result=memberService.updateMember(member);
        	if(result) {
        		return "Success";
        	}
        	else {
        		return "Fail";
        	}
    	}
    }
    
	private String upload(String imgoriname, byte[] bytes, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession(); 
		String root_path = session.getServletContext().getRealPath("/");
		String attach_path = "resources/fileUpload/";
		String savePath=root_path+""+attach_path;
		UUID uuid=UUID.randomUUID();
		String savefilename=uuid+"_"+imgoriname;
		File target=new File(savePath,savefilename);
		FileCopyUtils.copy(bytes,target);
		return savefilename;	
	}
	 @RequestMapping(value="/googleApiLogin", method=RequestMethod.GET)	   
	private ModelAndView googleApiLogin(@RequestParam("googleid") String googleid) {		 
		mav=new ModelAndView();
		boolean result=memberService.googleIdCheck(googleid);
		if(result) {
			mav.setViewName("home");
		}
		else {
			mav.addObject("kind", "nomal");
			mav.addObject("googleid", googleid);
			mav.setViewName("member/JoinForm");
		
		}
		return mav;
		
	 }
	
		@RequestMapping(value = "/jsjkakaoJoin", method = RequestMethod.GET)
		public ModelAndView kakaoJoin(@RequestParam("code") String code,HttpSession session) {
			mav=new ModelAndView();
			JsonNode token = KakaoJoinApi.getAccessToken(code);
			JsonNode profile =KakaoJoinApi.getKakaoUserInfo(token.path("access_token"));
			mav=kakaoService.kakaoJoin(profile);
			return mav;
		}
	
		@RequestMapping(value = "/kakaoCreate", method = RequestMethod.GET)
		public ModelAndView kakao() {
			String kakaoUrl=KakaoJoinApi.getAuthorizationUrl(session);
			mav=new ModelAndView();
			mav.addObject("kakaourl",kakaoUrl);
			mav.setViewName("member/KakaoCreate");
			return 	mav;
		}
	
		@RequestMapping(value = "/createKakaoMembers", method = RequestMethod.GET)
		public ModelAndView createKakaoMembers(@RequestParam("kakaoid") String kakaoid,@RequestParam("kind") String kind) {
			mav=new ModelAndView();
			mav.addObject("kind",kind);
			mav.addObject("kakaoid",kakaoid);
			mav.setViewName("member/JoinForm");
			return 	mav;
		}
	
	

	

	
}
