package com.icia.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dao.MemberDAO;
import com.icia.member.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	@Autowired
	private HttpSession session;
	
	private ModelAndView mav;

	public ModelAndView createMember(MemberDTO member) {
		mav=new ModelAndView();
		int result=dao.createMember(member);
		if(result>0) {
			mav.setViewName("Welcome");
		}
		else {
			mav.setViewName("Fail");
		}
		return mav;
	}

	public ModelAndView loginMember(MemberDTO member) {
		mav=new ModelAndView();
		boolean result=dao.loginMember(member);
		if(result) {
			session.setAttribute("id", member.getId());
			mav.setViewName("Welcome");
		}
		else {
			mav.setViewName("Fail");
		}
		return mav;
	}

	public ModelAndView selectMember() {
		mav=new ModelAndView();
		List<MemberDTO> memberList=dao.selectMember();
		mav.addObject("memberList",memberList);
		mav.setViewName("Select");
		return mav;
	}

	public ModelAndView showDetail(String id) {
		mav=new ModelAndView();
		List<MemberDTO> memberList=dao.showDetail(id);
		mav.addObject("memberList",memberList);
		mav.setViewName("ShowDetail");
		return mav;
	}

	public ModelAndView deleteMember(String id) {
		mav=new ModelAndView();
		int result=dao.deleteMember(id);
		if(result>0) {
			session.invalidate();
			mav.setViewName("Welcome");
		}
		else {
			mav.setViewName("Fail");
		}
		return mav;
	}

	public ModelAndView modifyMember(String id) {
		mav=new ModelAndView();
		List<MemberDTO> memberList=dao.showDetail(id);
		mav.addObject("memberList",memberList);
		mav.setViewName("Modify");
		return mav;
	}

	public ModelAndView updateMember(MemberDTO member) {
		mav=new ModelAndView();
		int result=dao.updateMember(member);
		if(result>0) {
			mav.setViewName("Welcome");
		}
		else {
			mav.setViewName("Fail");
		}
		return mav;
	}
	
	public ModelAndView adminDeleteMember(String id) {
		mav=new ModelAndView();
		int result=dao.deleteMember(id);
		if(result>0) {
			mav.setViewName("redirect:/goSelect");
		}
		else {
			mav.setViewName("redirect:/goSelect");
		}
		return mav;
	}

}
