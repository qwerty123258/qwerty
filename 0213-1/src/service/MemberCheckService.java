package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberCheckService {

	public boolean checkMember(String id,String password, MemberDTO member) {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result =dao.checkMember(id,password,member);
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
