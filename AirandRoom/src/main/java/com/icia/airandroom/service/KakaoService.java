package com.icia.airandroom.service;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.airandroom.dao.MemberDAO;
import com.icia.airandroom.dto.MemberDTO;


@Service
public class KakaoService {
	
	@Autowired
	private MemberDAO mdao;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;

	public ModelAndView kakaoJoin(JsonNode profile) {
		mav = new ModelAndView();
		String id=profile.get("id").asText();
		int result=mdao.kakaoCheck(id);
		if(result>0) {
			MemberDTO member = mdao.kakaoLogin(id);
			session.setAttribute("id", member.getId());
			session.setAttribute("kind", "normal");
			mav.setViewName("redirect:/main");
			return mav;
		}
		else {
			mav.addObject("kind","normal");
			mav.addObject("kakaoid",id);
			mav.setViewName("redirect:createKakaoMembers");
			return mav;
		}
	}
	
}
