package com.icia.airandroom.service;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.airandroom.dao.MemberDAO;
import com.icia.airandroom.dto.MemberDTO;
import com.icia.airandroom.page.Paging;

@Service
public class MemberService {

	private ModelAndView mav;
	
	@Autowired
	private MemberDAO mdao;
	
	@Autowired
	private HttpSession session;

	public boolean checkID(String id) {
		boolean result=mdao.checkID(id);
		if(result) {
			return true;
		}
		return false;
		
	}

	public boolean createMember(MemberDTO member) {
		boolean result=mdao.createMember(member);
		if(result) {
			return true;
		}
		return false;
	}

	public boolean checkEmail(String email) {
		boolean result=mdao.checkEmail(email);
		if(result) {
			return true;
		}
		return false;
	}

	public boolean login(MemberDTO member) {
		boolean result=mdao.login(member);
		if(result) {
			return true;
		}
		return false;
	}

	public boolean searchID(MemberDTO member) {
		boolean result=mdao.searchID(member);
		if(result) {
			return true;
		}
		return false;
	}

	public boolean searchPW(MemberDTO member) {
		boolean result=mdao.searchPW(member);
		if(result) {
			return true;
		}
		return false;
	}

	public void searchKind(MemberDTO member) {
		mdao.searchKind(member);
	}

	public boolean deleteMember(String id, HttpServletRequest request) {
		boolean result=mdao.deleteMember(id,request);
		if(result) {
			return true;
		}
		return false;
	}

	public ModelAndView modifyMembers(String id) {
		MemberDTO member = mdao.modifyMembers(id);
		mav=new ModelAndView();
		String phone[]=member.getPhone().split("-");
		mav.addObject("member", member);
		mav.addObject("phonefirst", phone[0]);
		mav.addObject("phonesecond", phone[1]);
		mav.addObject("phonelast", phone[2]);
		mav.setViewName("member/ModifyMember");
		return mav;
	}

	public boolean updateMember(MemberDTO member) {
		boolean result=mdao.updateMember(member);
		if(result) {
			return true;
		}
		return false;
	}

	public ModelAndView memberList(Paging paging) {
		mav=new ModelAndView();
		List<MemberDTO> memberList=mdao.memberList(paging);
		mav.addObject("memberList", memberList);
		mav.addObject("paging",paging);
		mav.setViewName("member/MemberList");
		return mav;
	}

	public int countMember() {
		int count=mdao.countMember();
		return count;
	}

	public boolean emailUpdate(MemberDTO member) {
		return mdao.emailUpdate(member);
	}
	
	public boolean googleIdCheck(String googleid) {
		String result=mdao.googleIdCheck(googleid);
		if(result!=null) {
			session.setAttribute("kind", "normal");
			session.setAttribute("id", result);
			
			return true;
			
		}
		return false;
	}
	
    public void keepLogin(String uid, String sessionId, Date next) {
        mdao.keepLogin(uid, sessionId, next);
    }
 
    public MemberDTO checkUserWithSessionKey(String sessionId) {
        return mdao.checkUserWithSessionKey(sessionId);
    }

	public boolean checkperiod(MemberDTO member) {
		return mdao.checkperiod(member);
	}

	public String usersEmail(String id) {
		return mdao.usersEmail(id);
	}

}
