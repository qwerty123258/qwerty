package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public int countMovieBoard() {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.countMovieBoard();
		close(con);
		return result;
	}

	public List<BoardDTO> movieList(Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList = dao.movieList(paging);
		close(con);
		return boardList;
	}
	
	public int countDramaBoard() {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.countDramaBoard();
		close(con);
		return result;
	}

	public List<BoardDTO> dramaList(Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList = dao.dramaList(paging);
		close(con);
		return boardList;
	}
	
	public int countUtilBoard() {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.countUtilBoard();
		close(con);
		return result;
	}

	public List<BoardDTO> utilList(Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList = dao.utilList(paging);
		close(con);
		return boardList;
	}
	public int countOtherBoard() {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.countOtherBoard();
		close(con);
		return result;
	}

	public List<BoardDTO> otherList(Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> boardList = dao.otherList(paging);
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

	public void deleteBoard(String bno) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.deleteBoard(bno);
		if(result>0){
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
			
		}
		
	}

	public void updateBoard(String title, String category, String content, String bimgfile, String bno) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.updateBoard(title,category,content,bimgfile,bno);
		if(result>0){
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
			
		}
		
	}

	public String checkUploder(String bno) {
		String id="";
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		id=dao.checkUploder(bno);
		close(con);
		return id;
	}

	public void checkLike(String bno, BoardDTO board) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		dao.checkLike(bno,board);
		close(con);
	}

	public void checkReport(String bno, BoardDTO board) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		dao.checkReport(bno,board);
		close(con);
	}

	public List<BoardDTO> likeTopList() {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> likeList = dao.likeTopList();
		close(con);
		return likeList;
	}

	public List<BoardDTO> viewTopList() {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> viewList = dao.viewTopList();
		close(con);
		return viewList;
	}

	public List<BoardDTO> latestTopList() {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> latestList = dao.latestTopList();
		close(con);
		return latestList;
	}

	public int countUserBoard(String id) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int count=dao.countUserBoard(id);
		close(con);
		return count;
	}

	public int searchCount(String keyword) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int count=dao.searchCount(keyword);
		close(con);
		return count;
	}

	public List<BoardDTO> searchList(String keyword, Paging paging) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<BoardDTO> searchList=new ArrayList<BoardDTO>();
		searchList=dao.searchList(keyword,paging);
		close(con);
		return searchList;
	}



}
