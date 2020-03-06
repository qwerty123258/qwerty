package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.HistoryDAO;

public class HistoryService {

	public boolean addHistory(String id, String action) {
		HistoryDAO dao=HistoryDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.addHistory(id,action);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
	}

	public boolean checkHistory(String id, String action) {
		HistoryDAO dao=HistoryDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result=dao.checkHistory(id,action);
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
