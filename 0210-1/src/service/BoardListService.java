package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.BoardDAO;
import dto.BoardDTO;

public class BoardListService {

	public List<BoardDTO> boardList() {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList= new ArrayList<BoardDTO>();
		boardList=dao.boardList();
		close(con);
		return boardList;
		
	}

	public List<BoardDTO> boardListBviewOrder() {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList= new ArrayList<BoardDTO>();
		boardList=dao.boardListBviewOrder();
		close(con);
		return boardList;
	}

}
