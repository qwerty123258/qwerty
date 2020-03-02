package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDAO {
	private static LikeDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static LikeDAO getInstance() {
		if(dao==null) {
			dao=new LikeDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int like(String bno, String id) {
		String sql="insert into likes values(?,?,SYSDATE)";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.setString(2, id);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int likeCancel(String bno, String id) {
		String sql="delete likes where bno=? and id=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.setString(2, id);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int likeCount(String bno) {
		String sql="select count(*) as count from likes where bno=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
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
}
