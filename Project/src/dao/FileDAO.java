package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileDAO {
	private static FileDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static FileDAO getInstance() {
		if(dao==null) {
			dao=new FileDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int addfile(String file, String fileoriname, String string) {
		String sql = "insert into bfiles values(bfile_seq.nextval,?,?,?,board_seq.currval)";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, file);
			pstmt.setString(2, fileoriname);
			pstmt.setString(3, string);
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
