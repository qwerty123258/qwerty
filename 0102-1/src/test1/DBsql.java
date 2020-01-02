package test1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBsql {
	Connection con = null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	public void Connection() {
		con=DBcon.DBConnection();
	}
}
