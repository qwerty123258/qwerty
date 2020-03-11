package com.icia.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	
	@Autowired
	private SqlSessionTemplate sql;

	public int createMember(MemberDTO member) {
		return sql.insert("Member.createMember", member);
	}

	public boolean loginMember(MemberDTO member) {
		String result=sql.selectOne("Member.loginCheck", member);
		return (result==null) ? false:true;
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

	public int deleteMember(String id) {
		return sql.delete("Member.delete", id);
	}

	public int updateMember(MemberDTO member) {
		return sql.update("Member.update", member);
	}
	

}
