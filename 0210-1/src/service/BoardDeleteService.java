package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;

public class BoardDeleteService {

	public boolean deleteBoard(String bnum) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.deleteBoard(bnum);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
			rollback(con);
			close(con);
		return false;
		
	}

}
