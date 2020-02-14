package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;

public class MemberArticlecountService {

	public int ArticleCount(String id) {
		int count=0;
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		count=dao.ArticleCount(id);
		close(con);
		return count;
		
	}

}
