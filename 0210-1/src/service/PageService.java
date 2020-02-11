package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.BoardDAO;
import dto.BoardDTO;
import page.Paging;

public class PageService {

	public int BoardCount() {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int count = dao.getAllCount();
		close(con);
		return count;
	}

	public List<BoardDTO> BoardList(Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList = dao.BoardList(paging);
		close(con);
		return boardList;
	}

	public List<BoardDTO> BoardListOrder(Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList = dao.BoardListOrder(paging);
		close(con);
		return boardList;
	}

}
