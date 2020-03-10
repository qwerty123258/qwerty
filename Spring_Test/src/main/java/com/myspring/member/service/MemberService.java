package com.myspring.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.member.dao.MemberDAO;
import com.myspring.member.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	private ModelAndView mav;

	public ModelAndView createMember(MemberDTO member) {
		mav=new ModelAndView();
		int result=dao.createMember(member);
		if(result>0) {
			mav.setViewName("Main");
		}
		else {
			mav.setViewName("Fail");
		}
		return mav;
	}

}
