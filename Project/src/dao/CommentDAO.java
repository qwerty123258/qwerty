package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CommentDTO;

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
	public List<CommentDTO> commentList(String bno) {
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		String sql="select * from comments where bno=? order by cno";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CommentDTO comment = new CommentDTO();
				comment.setCno(rs.getString("cno"));
				comment.setId(rs.getString("id"));
				comment.setCcontent(rs.getString("ccontent"));
				comment.setWritedate(rs.getString("writedate"));
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
}
