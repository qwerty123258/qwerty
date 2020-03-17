package com.icia.myproject.service;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.myproject.dao.MemberDAO;
import com.icia.myproject.dto.MemberDTO;

@Service
public class KakaoService {
	
	@Autowired
	private MemberDAO dao;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;

	public ModelAndView kakaoJoin(JsonNode profile) {
		mav = new ModelAndView();
		String id=profile.get("id").asText();
		int result=dao.kakaoCheck(id);
		if(result>0) {
			MemberDTO member = dao.kakaoLogin(id);
			session.setAttribute("id", member.getId());
			mav.setViewName("redirect:/home");
			return mav;
		}
		else {
			mav.addObject("kakaoid",id);
			mav.setViewName("member/CreateMember");
			return mav;
		}
	}
	
}
