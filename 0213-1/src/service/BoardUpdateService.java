package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;

public class BoardUpdateService {

	public boolean updateBoard(String bnum, String title, String bcontent, String bimgfile, String bfile) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.updateBoard(bnum,title,bcontent,bimgfile,bfile);
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
