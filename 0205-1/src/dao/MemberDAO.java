package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.MemberDTO;

public class MemberDAO {
	private static MemberDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static MemberDAO getInstance() {
		//싱글톤 방식 : 객체를 한번만 생성후 상주시켜 메모리 소모를 줄여줌.
		if(dao==null) {
			dao=new MemberDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
//	public int MemberDB(String id, String password, String name, String birth, String gender, String email) {
//		String sql = "insert into Member values(?,?,?,?,?,?)";
//		int result=0;
//		try {
//		pstmt = con.prepareStatement(sql); 
//		pstmt.setString(1, id);
//		pstmt.setString(2, password);
//		pstmt.setString(3, name);
//		pstmt.setString(4, birth);
//		pstmt.setString(5, gender);
//		pstmt.setString(6, email);
//		result=pstmt.executeUpdate();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			close(pstmt);
//		}
//		return result;
//	}
	public List<MemberDTO> selectMember() {
		String sql = "select * from Member";
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
		pstmt = con.prepareStatement(sql); 
		rs=pstmt.executeQuery();
		while(rs.next()) {
			MemberDTO member = new MemberDTO();
			member.setId(rs.getString("id"));
			member.setPassword(rs.getString("password"));
			member.setName(rs.getString("name"));
			member.setBirth(rs.getString("birth"));
			member.setGender(rs.getString("gender"));
			member.setEmail(rs.getString("email"));
			memberList.add(member);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return memberList;
	}
	public int MemberDB(MemberDTO member) {
		String sql = "insert into Member values(?,?,?,?,?,?)";
		int result=0;
		try {
		pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, member.getId());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		pstmt.setString(4, member.getBirth());
		pstmt.setString(5, member.getGender());
		pstmt.setString(6, member.getEmail());
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
	
}
