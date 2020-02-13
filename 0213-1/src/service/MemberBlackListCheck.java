package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberBlackListCheck {

	public boolean blackListCheck(String id) {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result=dao.blackListCheck(id);
		if(result) {
			return true;
		}
		return false;
		
	}

}
