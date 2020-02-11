package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.BoardDAO;
import dto.BoardDTO;

public class DetailService {

	public void detail(String bnum,BoardDTO board) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		dao.detail(bnum,board);
		close(con);
	}

	public void bViewIncrease(String bnum) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.bViewIncrease(bnum);
		if(result>0) {
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
		}
		
	}

}
