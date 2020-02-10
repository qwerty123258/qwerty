package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static db.JdbcUtil.*;

import dto.BoardDTO;

public class BoardDAO {
	private static BoardDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static BoardDAO getInstance() {
		//싱글톤 방식 : 객체를 한번만 생성후 상주시켜 메모리 소모를 줄여줌.
		if(dao==null) {
			dao=new BoardDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int writeBoard(BoardDTO board) {
		String sql="insert into Board values(board_seq.nextval,?,?,?,sysdate,?,0)";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getBcontent());
			pstmt.setString(4, board.getPassword());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
		
	}
	public List<BoardDTO> boardList() {
		String sql="select * from Board";
		List<BoardDTO> boardList= new ArrayList<BoardDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO board=new BoardDTO();
				board.setBnum(rs.getInt("bnum"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setWritedate(rs.getString("writedate"));
				board.setBview(rs.getInt("bview"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return boardList;
	}
	public List<BoardDTO> detail(String bnum) {
		String sql="select * from Board where bnum=?";
		List<BoardDTO> boardList= new ArrayList<BoardDTO>();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bnum);
			rs=pstmt.executeQuery();
			while(rs.next()){
				BoardDTO board=new BoardDTO();
				board.setBnum(rs.getInt("bnum"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setWritedate(rs.getString("writedate"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBview(rs.getInt("bview"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return boardList;
	}
	public int bViewIncrease(String bnum) {
		String sql="update Board set bview=bview+? where bnum=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, bnum);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
		
	}
	public int updateBoard(String bnum, String title, String bcontent) {
		String sql="update Board set title=?,bcontent=? where bnum=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, bcontent);
			pstmt.setString(3, bnum);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
}
