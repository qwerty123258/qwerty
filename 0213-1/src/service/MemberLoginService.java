package service;

import static db.JdbcUtil.*;

import java.sql.*;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberLoginService {

	public boolean memberLogin(String id, String password, MemberDTO member) {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result=dao.memberLoginDAO(id,password,member);
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
