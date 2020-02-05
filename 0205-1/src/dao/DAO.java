package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dto.SelectDTO;

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
	public int inputDB(String data1) {
		String sql = "insert into nametest values(?)";
		int result=0;
		try {
		pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, data1);
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
	public List<SelectDTO> selectDB() {
		String sql = "select * from nametest";
		List<SelectDTO> dataList = new ArrayList<SelectDTO>();
		try {
		pstmt = con.prepareStatement(sql); 
		rs=pstmt.executeQuery();
		while(rs.next()) {
			SelectDTO sel = new SelectDTO();
			sel.setData1(rs.getString("name"));
			dataList.add(sel);
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
