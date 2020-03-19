package com.icia.myproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.myproject.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sql;
	
	public boolean checkMember(String id) {
		String result=sql.selectOne("Member.checkMember", id);
		return (result==null) ? false:true;
	}

	public int createMember(MemberDTO member) {
		if(member.getKakaoid()!=null) {
			return sql.insert("Member.createKakao", member);
		}
		else if(member.getNaverid()!=null) {
			return sql.insert("Member.createNaver", member);
		}
		else {
			return sql.insert("Member.create", member);
		}
	}

	public boolean login(MemberDTO member) {
		int result=sql.selectOne("Member.login", member);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}
	public MemberDTO kakaoLogin(String id) {
		return sql.selectOne("Member.kakaoLogin", id);
	}

	public MemberDTO naverLogin(String naverid) {
		return sql.selectOne("Member.naverLogin", naverid);
	}

	public int kakaoCheck(String id) {
		return sql.selectOne("Member.kakaoCheck", id);
	}

	public int naverCheck(String naverid) {
		return sql.selectOne("Member.naverCheck", naverid);
	}

	public MemberDTO myProfile(String id) {
		return sql.selectOne("Member.myProfile", id);
	}

	public int deleteProfile(MemberDTO member) {
		return sql.update("Member.deleteProfile", member);
	}

	public int updateProfile(MemberDTO member) {
		return sql.update("Member.updateProfile", member);
	}

	public List<MemberDTO> selectMember() {
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		memberList=sql.selectList("Member.select");
		return memberList;
	}

	public List<MemberDTO> showDetail(String id) {
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		memberList=sql.selectList("Member.detail",id);
		return memberList;
	}

	public int adminDelete(String id) {
		return sql.delete("Member.admin", id);
	}

}
