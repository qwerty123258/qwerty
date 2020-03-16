package com.icia.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.icia.member.api.KakaoJoinApi;
import com.icia.member.api.NaverJoinApi;
import com.icia.member.dto.MemberDTO;
import com.icia.member.service.KakaoService;
import com.icia.member.service.MemberService;
import com.icia.member.service.NaverService;

@Controller
public class HomeController {
	//서비스 호출시
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private NaverService naverService;
	
	@Autowired
	private NaverJoinApi naverJoinApi;
	
	@Autowired
	private HttpSession session;
	
	//데이터와 목적지를 담아둔 클래스
	private ModelAndView mav;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "Welcome";
	}
	
	@RequestMapping(value = "/Welcome", method = RequestMethod.GET)
	public String welcome() {
		return "Welcome";
	}
	@RequestMapping(value = "/CreateMembers", method = RequestMethod.GET)
	public String create() {
		
		return 	"CreateMember";
	}
	
	@RequestMapping(value = "/jsjNaverJoin", method = RequestMethod.GET)
	public ModelAndView NaverJoin(@RequestParam("code") String code,@RequestParam("state") String state,HttpSession session) throws IOException, ParseException {
		mav =new ModelAndView();
			OAuth2AccessToken oauthToken =naverJoinApi.getAccessToken(session, code, state);
			String profile = naverJoinApi.getUserProfile(oauthToken);
			mav=naverService.naverJoin(profile);
		    return mav;
		
	}
	
	
	@RequestMapping(value = "/NaverCreate", method = RequestMethod.GET)
	public ModelAndView Naver(HttpSession session) {
		String naverUrl=naverJoinApi.getAuthorizationUrl(session);
		mav=new ModelAndView();
		mav.addObject("naverUrl",naverUrl);
		mav.setViewName("NaverCreate");
		return 	mav;
	}
	
	@RequestMapping(value = "/jsjkakaoJoin", method = RequestMethod.GET)
	public ModelAndView kakaoJoin(@RequestParam("code") String code,HttpSession session) {
		mav=new ModelAndView();
		JsonNode token = KakaoJoinApi.getAccessToken(code);
		JsonNode profile =KakaoJoinApi.getKakaoUserInfo(token.path("access_token"));
		mav=kakaoService.kakaoJoin(profile);
		return mav;
	}
	
	@RequestMapping(value = "/KakaoCreate", method = RequestMethod.GET)
	public ModelAndView kakao() {
		String kakaoUrl=KakaoJoinApi.getAuthorizationUrl(session);
		mav=new ModelAndView();
		mav.addObject("kakaourl",kakaoUrl);
		mav.setViewName("KakaoCreate");
		return 	mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ShowDetailAjax", method = RequestMethod.POST)
	public MemberDTO showDetailAjax(@RequestParam("id") String id) {
		MemberDTO member=memberService.showDetailAjax(id);
		return member;
}
	
	@ResponseBody
	@RequestMapping(value = "/CheckUser", method = RequestMethod.POST)
	public String checkUser(@RequestParam("id") String id) {
		boolean result=memberService.checkUser(id);
		if(result) {
			return "idOverlap";
		}
		else {
			return "idNotOverlap";
		}
}
	
	@RequestMapping(value = "/goAdminDelete", method = RequestMethod.GET)
	public ModelAndView adminDeleteMember(@RequestParam("id") String id) {
	mav= new ModelAndView();
	mav=memberService.adminDeleteMember(id);
	return mav;
}
	
	@RequestMapping(value = "/Update", method = RequestMethod.POST)
	public ModelAndView updateMember(@ModelAttribute MemberDTO member) {
	mav= new ModelAndView();
	mav=memberService.updateMember(member);
	return mav;
}
	
	@RequestMapping(value = "/goModify", method = RequestMethod.GET)
	public ModelAndView modifyMember(@RequestParam("id") String id) {
	mav= new ModelAndView();
	mav=memberService.modifyMember(id);
	return mav;
}

	@RequestMapping(value = "/goDelete", method = RequestMethod.GET)
	public ModelAndView deleteMember(@RequestParam("id") String id) {
	mav= new ModelAndView();
	mav=memberService.deleteMember(id);
	return mav;
}
	
	@RequestMapping(value = "/goLogout", method = RequestMethod.GET)
	public String logout() {
		session.invalidate();
		return "redirect:/Welcome";
	}
	
	@RequestMapping(value = "/ShowDetail", method = RequestMethod.GET)
	public ModelAndView showDetail(@RequestParam("id") String id) {
	mav= new ModelAndView();
	mav=memberService.showDetail(id);
	return mav;
}
	
	@RequestMapping(value = "/goSelect", method = RequestMethod.GET)
	public ModelAndView select() {
		mav= new ModelAndView();
		mav=memberService.selectMember();
		return 	mav;
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
		public String loginMember(@ModelAttribute MemberDTO member) {
		boolean result=memberService.loginMember(member);
		if(result) {
			return "redirect:/Welcome";
		}
		else {
			
			return "Fail";
		}
	}
	
	@RequestMapping(value="/CreateMember",method=RequestMethod.POST)
	public String createUser(@ModelAttribute MemberDTO member) {
		boolean result=memberService.createMember(member);
		if(result) {
			return "redirect:/Welcome";
		}
		else {
			return "Fail";
		}
	}
	
}
