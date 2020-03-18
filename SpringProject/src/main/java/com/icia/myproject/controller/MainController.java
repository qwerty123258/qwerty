package com.icia.myproject.controller;


import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.icia.myproject.api.KakaoJoinApi;
import com.icia.myproject.api.NaverJoinApi;
import com.icia.myproject.service.KakaoService;
import com.icia.myproject.service.NaverService;
import com.icia.myproject.dto.MemberDTO;
import com.icia.myproject.service.MemberService;

@Controller
public class MainController {
	
	private ModelAndView mav;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private NaverService naverService;
	
	@Autowired
	private NaverJoinApi naverJoinApi;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String gohome() {
		return "home";
	}
	
	@RequestMapping(value = "/fail", method = RequestMethod.GET)
	public String fail() {
		return "member/fail";
	}
	
	@RequestMapping(value = "/goCreateMember", method = RequestMethod.GET)
	public String goCreate() {
		return "member/CreateMember";
	}
	
	@ResponseBody
	@RequestMapping(value = "/CheckMember", method = RequestMethod.POST)
	public String CheckMember(@RequestParam("id") String id) {
		boolean result=memberService.checkMember(id);
		if(result) {
			return "idOverlap";
		}
		else {
			return "idNotOverlap";
		}
	}
	
	@RequestMapping(value = "/CreateMember", method = RequestMethod.POST)
	public ModelAndView createMember(@ModelAttribute MemberDTO member) throws IOException {
		mav=new ModelAndView();
		mav=memberService.createMember(member);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(@ModelAttribute MemberDTO member) {
		boolean result=memberService.login(member);
		if(result) {
			session.setAttribute("id", member.getId());
			return "Success";
		}
		else {
			return "Fail";
		}
	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String logout() {
		session.invalidate();
		return "redirect:/home";
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
		mav.setViewName("member/NaverCreate");
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
		mav.setViewName("member/KakaoCreate");
		return 	mav;
	}
	@RequestMapping(value = "/goWrite", method = RequestMethod.GET)
	public String goWrite() {
		return "board/BoardWrite";
	}
	
	@RequestMapping(value = "/goChangeProfile", method = RequestMethod.GET)
	public String goChangeProfile() {
		return "member/ChangeProfile";
	}
	
	@ResponseBody
	@RequestMapping(value = "/MyProfile", method = RequestMethod.POST)
	public MemberDTO myProfile() {
		String id= (String) session.getAttribute("id");
		MemberDTO myProfile=memberService.myProfile(id);
		return myProfile;
	}
	
	@RequestMapping(value = "/DeleteProfile", method = RequestMethod.GET)
	public String deleteProfile(@ModelAttribute MemberDTO member) {
		String id= (String) session.getAttribute("id");
		member.setId(id);
		boolean result=memberService.deleteProfile(member);
		if(result) {
			return "redirect:/home";
		}
		else {
			return "redirect:/home";
		}
	}
	
	@RequestMapping(value = "/ProfileUpdate", method = RequestMethod.POST)
	public String profileUpdate(@ModelAttribute MemberDTO member) throws IOException {
		String id= (String) session.getAttribute("id");
		member.setId(id);
		boolean result=memberService.updateProfile(member);
		if(result) {
			return "redirect:/home";
		}
		else {
			return "redirect:/home";
		}
	}
}
