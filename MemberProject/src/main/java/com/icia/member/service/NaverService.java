package com.icia.member.service;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dao.MemberDAO;
import com.icia.member.dto.MemberDTO;

@Service
public class NaverService {
	
	@Autowired
	private MemberDAO dao;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;

	public ModelAndView naverJoin(String profile) throws ParseException {
		mav=new ModelAndView();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);
		JSONObject naverUser = (JSONObject) obj;
		JSONObject userInfo = (JSONObject) naverUser.get("response");
		String naverid=(String) userInfo.get("id");
		MemberDTO member = dao.naverLogin(naverid);
		if(member.getId()==null) {
			mav.addObject("naverid",naverid);
			mav.setViewName("CreateMember");
			return mav;
		}
		else {
			session.setAttribute("id", member.getId());
			mav.setViewName("redirect:/Welcome");
			return mav;
		}
	}

}
