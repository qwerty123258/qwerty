package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DownloadDAO;

public class DownloadService {

	public void addHistory(String id, String bfno, String bfile, String bno) {
		DownloadDAO dao=DownloadDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.addHistory(id,bfno,bfile,bno);
		if(result>0) {
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
		}
		
	}

	public boolean checkHistory(String id, String bfno) {
		DownloadDAO dao=DownloadDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result=dao.checkHistory(id,bfno);
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
