package service;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.BoardDAO;

public class CommentPageService {

	public int CommentCount(String bnum) {
		int count=0;
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		count=dao.CommentCount(bnum);
		return count;
	}

}
