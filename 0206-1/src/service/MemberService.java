package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberService {

//	public boolean memberDB(String id, String password, String name, String birth, String gender, String email) {
//		MemberDAO dao=MemberDAO.getInstance();
//		Connection con=getConnection();
//		dao.setConnection(con);
//		int result=dao.MemberDB(id,password,name,birth,gender,email);
//		if(result>0) {
//			commit(con);
//			close(con);
//			return true;
//		}
//		else {
//			rollback(con);
//		}
//		close(con);
//		return false;
//	}

	public boolean memberDB(MemberDTO member) {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.MemberDB(member);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
		}
		close(con);
		return false;
	}

}
