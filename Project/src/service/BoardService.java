package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BoardDAO;
import dto.BoardDTO;
import page.Paging;

public class BoardService {

	public void writeBoard(String title, String category, String content, String bimgfile, String id) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.writeBoard(title,category,content,bimgfile,id);
		if(result>0) {
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
		}
		
	}

	public int countBoard() {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.countBoard();
		close(con);
		return result;
	}

	public List<BoardDTO> boardList(Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList = dao.boardList(paging);
		close(con);
		return boardList;
	}

	public void boardDetail(String bno, BoardDTO board) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		dao.boardDetail(bno,board);
		close(con);
		
	}

	public void bviewIncrease(String bno) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.bviewIncrease(bno);
		if(result>0){
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
			
		}
		
	}

}
