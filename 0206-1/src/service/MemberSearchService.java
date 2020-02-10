package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberSearchService {

	public boolean memberSearch(String name, String email, MemberDTO member) {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		dao.memberSearch(name,email,member);
		if(member.getId().equals("아이디없음")) {
			close(con);
			return false;
		}
		else {
			close(con);
			return true;
		}
		
	}

	public boolean memberSearchPassword(String id,String email, MemberDTO member) {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		dao.memberSearchPassword(id,email,member);
		if(member.getPassword().equals("비밀번호없음")) {
			close(con);
			return false;
		}
		else {
			close(con);
			return true;
		}
	}

}
