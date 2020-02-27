package dao;

import static db.JdbcUtil.close;
		
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDTO;
import page.Paging;

public class BoardDAO {
	private static BoardDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static BoardDAO getInstance() {
		if(dao==null) {
			dao=new BoardDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int writeBoard(String title, String category, String content, String bimgfile, String id) {
		String sql = "insert into board values(board_seq.nextval,?,?,?,SYSDATE,0,?,?)";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, id);
			pstmt.setString(4, bimgfile);
			pstmt.setString(5, category);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int countBoard() {
		String sql="select count(*) as count from board";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}
	public List<BoardDTO> boardList(Paging paging) {
	    int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
	    List<BoardDTO> boardList=new ArrayList<BoardDTO>();
        String sql = "SELECT b.*,to_char(writedate,'YYYY-MM-DD HH:MM') as bdate FROM  (select ROWNUM row_num,Board.* from Board) b  WHERE row_num >= ? and row_num <= ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setCategory(rs.getString("category"));
				board.setBno(rs.getString("bno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setBview(rs.getString("bview"));
				board.setWritedate(rs.getString("bdate"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}
	public void boardDetail(String bno, BoardDTO board) {
		String sql="select * from board where bno=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				board.setBno(rs.getString("bno"));
				board.setTitle(rs.getString("title"));
				board.setId(rs.getString("id"));
				board.setWritedate(rs.getString("writedate"));
				board.setBimgfile(rs.getString("bimgfile"));
				board.setBview(rs.getString("bview"));
				board.setContent(rs.getString("contents"));
				board.setCategory(rs.getString("category"));
			}
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		
	}
	public int bviewIncrease(String bno) {
		String sql="update Board set bview=bview+? where bno=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, bno);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int deleteBoard(String bno) {
		String sql="delete Board where bno=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bno);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int updateBoard(String title, String category, String content, String bimgfile, String bno) {
		String sql="update Board set title=?,category=?,contents=?,bimgfile=? where bno=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, category);
			pstmt.setString(3, content);
			pstmt.setString(4, bimgfile);
			pstmt.setString(5, bno);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public String checkUploder(String bno) {
		String id="";
		String sql="select * from board where bno=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				id=rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return id;
	}
}
