package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.BoardDAO;
import dto.BoardDTO;
import page.Paging;

public class KeywordSearchService {

	public List<BoardDTO> titleSearch(String keyword,Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList = dao.titleSearch(keyword,paging);
		close(con);
		return boardList;
		
	}

	public List<BoardDTO> writerSearch(String keyword, Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList = dao.writerSearch(keyword,paging);
		close(con);
		return boardList;
	}
	
	public List<BoardDTO> titleSearchOrder(String keyword,Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList = dao.titleSearchOrder(keyword,paging);
		close(con);
		return boardList;
		
	}

	public List<BoardDTO> writerSearchOrder(String keyword, Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList = dao.writerSearchOrder(keyword,paging);
		close(con);
		return boardList;
	}

	public int titleCount(String keyword) {
		int result=0;
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		result=dao.titlesearchCount(keyword); 
		close(con);
		return result;
	}
	
	public int writerCount(String keyword) {
		int result=0;
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		result=dao.writersearchCount(keyword); 
		close(con);
		return result;
	}
	
	

}
