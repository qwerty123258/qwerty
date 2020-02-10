package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberCheckOverlapService {
	public boolean checkOverlap(String id) {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result =dao.checkOverlap(id);
		if(result) {
			close(con);
			return true;
		}
		else {
			close(con);
			return false;
		}

	}
	public boolean checkOverlapEmail(String email) {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result =dao.checkOverlapEmail(email);
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
