package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DownloadDAO {
	private static DownloadDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static DownloadDAO getInstance() {
		if(dao==null) {
			dao=new DownloadDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int addHistory(String id, String bfno, String bfile, String bno) {
		int result=0;
		String sql="insert into downloads values(?,?,?,?,SYSDATE)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			pstmt.setString(2, bfno);
			pstmt.setString(3, bfile);
			pstmt.setString(4, id);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public boolean checkHistory(String id, String bfno) {
		String sql ="select * from downloads where id=? and bfno=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, bfno);
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
