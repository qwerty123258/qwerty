package service;

import static db.JdbcUtil.*;

import java.sql.*;

import dao.MemberDAO;

public class MemberLoginService {

	public boolean memberLogin(String id, String password) {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result=dao.memberLoginDAO(id,password);
		if(result) {
			close(con);
			return true;
		}
		else {
			close(con);
			return false;
		}
		
	}

}
