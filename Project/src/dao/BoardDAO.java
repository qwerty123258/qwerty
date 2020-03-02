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
	public int countMovieBoard() {
		String sql="select count(*) as count from board where category='movie'";
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
	public List<BoardDTO> movieList(Paging paging) {
	    int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
	    List<BoardDTO> boardList=new ArrayList<BoardDTO>();
        String sql = "SELECT b.*,to_char(writedate,'YYYY-MM-DD HH:MM') as bdate FROM  (select ROWNUM row_num,a.*,u.blacklist from Board a,Users u where category='movie' and u.id=a.id) b  WHERE row_num >= ? and row_num <= ?";
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
				board.setBlacklist(rs.getString("blacklist"));
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
	
	public int countDramaBoard() {
		String sql="select count(*) as count from board where category='drama'";
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
	public List<BoardDTO> dramaList(Paging paging) {
	    int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
	    List<BoardDTO> boardList=new ArrayList<BoardDTO>();
        String sql = "SELECT b.*,to_char(writedate,'YYYY-MM-DD HH:MM') as bdate FROM  (select ROWNUM row_num,a.*,u.blacklist from Board a,Users u where category='drama' and u.id=a.id) b  WHERE row_num >= ? and row_num <= ?";
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
				board.setBlacklist(rs.getString("blacklist"));
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
	public int countUtilBoard() {
		String sql="select count(*) as count from board where category='util'";
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
	public List<BoardDTO> utilList(Paging paging) {
	    int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
	    List<BoardDTO> boardList=new ArrayList<BoardDTO>();
        String sql = "SELECT b.*,to_char(writedate,'YYYY-MM-DD HH:MM') as bdate FROM  (select ROWNUM row_num,a.*,u.blacklist from Board a,Users u where category='util' and u.id=a.id) b  WHERE row_num >= ? and row_num <= ?";
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
				board.setBlacklist(rs.getString("blacklist"));
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
	public int countOtherBoard() {
		String sql="select count(*) as count from board where category='other'";
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
	public List<BoardDTO> otherList(Paging paging) {
	    int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
	    List<BoardDTO> boardList=new ArrayList<BoardDTO>();
        String sql = "SELECT b.*,to_char(writedate,'YYYY-MM-DD HH:MM') as bdate FROM  (select ROWNUM row_num,a.*,u.blacklist from Board a,Users u where category='other' and u.id=a.id) b  WHERE row_num >= ? and row_num <= ?";
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
				board.setBlacklist(rs.getString("blacklist"));
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
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		
	}
	public void checkLike(String bno, BoardDTO board) {
		String sql="select b.*,l.id as likeuser from board b,likes l where b.bno=? and b.bno=l.bno";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				board.setLikeuser(rs.getString("likeuser"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		
	}
	public void checkReport(String bno, BoardDTO board) {
		String sql="select b.*,r.id as reportuser from board b,reports r where b.bno=? and b.bno=r.bno";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				board.setReportuser(rs.getString("reportuser"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
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
