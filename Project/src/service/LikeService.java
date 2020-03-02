package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.LikeDAO;

public class LikeService {

	public boolean like(String bno, String id) {
		LikeDAO dao=LikeDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.like(bno,id);
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

	public boolean likeCancel(String bno, String id) {
		LikeDAO dao=LikeDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.likeCancel(bno,id);
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

	public int likeCount(String bno) {
		LikeDAO dao=LikeDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.likeCount(bno);
		close(con);
		return result;
	}

}
