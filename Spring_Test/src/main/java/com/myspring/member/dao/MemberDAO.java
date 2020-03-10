package com.myspring.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.member.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	
	@Autowired
	private SqlSessionTemplate sql;

	public int createMember(MemberDTO member) {
		return sql.insert("Member.createMember", member);
	}
	

}
