package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryDAO {
	private static HistoryDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static HistoryDAO getInstance() {
		if(dao==null) {
			dao=new HistoryDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int addHistory(String id, String action) {
		String sql="insert into history values(?,?,SYSDATE)";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, action);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public boolean checkHistory(String id, String action) {
		String sql="select * from history where id=? and writedate<sysdate and writedate>sysdate-1 and action=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, action);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return false;
	}
}
