package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.BoardDAO;
import dto.CommentDTO;

public class CommentService {

	public boolean writeComment(String bnum, String writer, String ccontent) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.writeComment(bnum,writer,ccontent);
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

	public List<CommentDTO> selectComment(String bnum) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		commentList=dao.selectComment(bnum);
		close(con);
		return commentList;
		
	}

}
