package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.ReportDAO;

public class ReportService {

	public int reportCount(String bno) {
		ReportDAO dao=ReportDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int count=dao.reportCount(bno);
		close(con);
		return count;
	}

	public boolean report(String bno,String id) {
		ReportDAO dao=ReportDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.report(bno,id);
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

	public boolean reportCancel(String bno, String id) {
		ReportDAO dao=ReportDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.reportCancel(bno,id);
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

}
