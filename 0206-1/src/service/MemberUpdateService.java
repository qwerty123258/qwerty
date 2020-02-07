package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberUpdateService {

	public boolean updateMember(String id, String name, String email) {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.updateMember(id,name,email);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			close(con);
			return false;
		}
		
	}

}
