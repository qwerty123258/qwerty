package com.myspring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.member.dto.MemberDTO;
import com.myspring.member.service.MemberService;

@Controller
public class CreateMember {
	
	//서비스 호출시
	@Autowired
	private MemberService memberService;
	
	//데이터와 목적지를 담아둔 클래스
	private ModelAndView mav;
	
	//실행시 첫 화면
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		System.out.println("main 메소드");
		return "Main";
	}
	
	@RequestMapping(value="/goCreateMembers",method=RequestMethod.GET)
	public String goCreateUser() {
		System.out.println("goCreateMembers 메소드");
		return "CreateUser";
	}
	
	@RequestMapping(value="/CreateMember",method=RequestMethod.POST)
	public ModelAndView createUser(@ModelAttribute MemberDTO member) {
		System.out.println("CreateMember 메소드");
		System.out.println(member.toString());
		mav= new ModelAndView();
		mav=memberService.createMember(member);
		return mav;
	}
	
	
}
