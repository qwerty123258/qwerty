package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CommentDTO;
import page.Paging;

public class CommentDAO {
	private static CommentDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static CommentDAO getInstance() {
		if(dao==null) {
			dao=new CommentDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int addComments(String id, String bno, String content) {
		String sql = "insert into comments values(comments_seq.nextval,?,?,?,SYSDATE)";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.setString(2, id);
			pstmt.setString(3, content);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public List<CommentDTO> commentList(String bno, Paging paging) {
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
	    int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
        String sql = "SELECT c.*,to_char(writedate,'YYYY-MM-DD HH:MM') as cdate FROM  (select ROWNUM row_num,Comments.* from Comments where bno=? order by cno) c  WHERE row_num >= ? and row_num <= ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CommentDTO comment = new CommentDTO();
				comment.setCno(rs.getString("cno"));
				comment.setId(rs.getString("id"));
				comment.setCcontent(rs.getString("ccontent"));
				comment.setWritedate(rs.getString("cdate"));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return commentList;
	}
	public int countComment(String bno) {
		String sql="select count(*) as count from comments where bno=?";
		int count=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				count=rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		
		return count;
	}
	public int deleteComment(String cno) {
		int result=0;
		String sql ="delete comments where cno=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cno);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int updateComment(String cno, String content) {
		int result=0;
		String sql ="update comments set ccontent=? where cno=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, cno);
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
