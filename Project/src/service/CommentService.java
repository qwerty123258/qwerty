package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.CommentDAO;
import dto.CommentDTO;
import page.Paging;

public class CommentService {

	public boolean addComments(String id, String bno,String content) {
		CommentDAO dao=CommentDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.addComments(id,bno,content);
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

	public List<CommentDTO> commentList(String bno, Paging paging) {
		CommentDAO dao=CommentDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		commentList=dao.commentList(bno,paging);
		close(con);
		return commentList;
	}

	public int countComment(String bno) {
		CommentDAO dao=CommentDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int count=dao.countComment(bno);
		close(con);
		return count;
	}

}
