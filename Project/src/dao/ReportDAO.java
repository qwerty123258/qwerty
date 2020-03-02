package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import static db.JdbcUtil.close;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAO {
	private static ReportDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static ReportDAO getInstance() {
		if(dao==null) {
			dao=new ReportDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int reportCount(String bno) {
		String sql="select count(*) as count from reports where bno=?";
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
	public int report(String bno,String id) {
		String sql="insert into reports values(?,?,SYSDATE)";
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
	
	public int reportCancel(String bno,String id) {
		String sql="delete reports where bno=? and id=?";
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
}
