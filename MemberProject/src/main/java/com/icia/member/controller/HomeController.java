package com.icia.member.controller;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

@Controller
public class HomeController {
	//서비스 호출시
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	//데이터와 목적지를 담아둔 클래스
	private ModelAndView mav;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "Welcome";
	}
	@RequestMapping(value = "/CreateMembers", method = RequestMethod.GET)
	public String create() {
		
		return 	"CreateMember";
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
		return "Welcome";
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
		public ModelAndView loginMember(@ModelAttribute MemberDTO member) {
		mav= new ModelAndView();
		mav=memberService.loginMember(member);
		return mav;
	}
	
	@RequestMapping(value="/CreateMember",method=RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute MemberDTO member) {
		mav= new ModelAndView();
		mav=memberService.createMember(member);
		return mav;
	}
	
}
