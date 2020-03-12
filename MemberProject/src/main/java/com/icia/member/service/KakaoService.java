package com.icia.member.service;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dao.MemberDAO;
import com.icia.member.dto.MemberDTO;

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
		mav.addObject("kakaoid",id);
		mav.setViewName("CreateMember");
		return mav;
	}

	public ModelAndView kakaoLogin(JsonNode profile) {
		mav = new ModelAndView();
		String id=profile.get("id").asText();
		JsonNode kakaoaccount = profile.get("kakao_account");		
		JsonNode kakaoprofile= kakaoaccount.get("profile");
		String profileimg =kakaoprofile.path("thumbnail_image_url").asText();
		MemberDTO member=dao.kakaoLogin(id);
		session.setAttribute("id", member.getId());
		session.setAttribute("profileimg", profileimg);
		mav.setViewName("redirect:/Welcome");
		return mav;
	}
	
}
