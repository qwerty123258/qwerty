package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class DAO {
	private static DAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static DAO getInstance() {
		//싱글톤 방식 : 객체를 한번만 생성후 상주시켜 메모리 소모를 줄여줌.
		if(dao==null) {
			dao=new DAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int inputDB(String data1, String data2) {
		String sql = "insert into test values(?,?)";
		int result=0;
		try {
		pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, data1);
		pstmt.setString(2, data2);
		result=pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
		
	}
	public List<String> selectDB() {
		String sql = "select * from test";
		List<String> dataList = new ArrayList<String>();
		try {
		pstmt = con.prepareStatement(sql); 
		rs=pstmt.executeQuery();
		while(rs.next()) {
			dataList.add(rs.getString("data1"));
			dataList.add(rs.getString("data2"));
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return dataList;
	}
}
