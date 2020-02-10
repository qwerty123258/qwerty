package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			member.setBlacklist(rs.getString("blacklist"));
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
		String sql = "insert into Member values(?,?,?,?,?,?,?)";
		int result=0;
		try {
		pstmt = con.prepareStatement(sql); 
		pstmt.setString(1, member.getId());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		pstmt.setString(4, member.getBirth());
		pstmt.setString(5, member.getGender());
		pstmt.setString(6, member.getEmail());
		pstmt.setString(7, member.getBlacklist());
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
	public boolean memberLoginDAO(String id, String password) {
		String sql = "select * from Member where id=? and password=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
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
	public List<MemberDTO> modifyMember(String id) {
		String sql = "select * from Member where id=?";
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			MemberDTO member = new MemberDTO();
			member.setId(rs.getString("id"));
			member.setPassword(rs.getString("password"));
			member.setName(rs.getString("name"));
			member.setBirth(rs.getString("birth"));
			member.setGender(rs.getString("gender"));
			member.setEmail(rs.getString("email"));
			member.setBlacklist(rs.getString("blacklist"));
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
	public int updateMember(String id, String name, String email) {
		String sql = "update Member set name=?,email=? where id=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, id);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
		
	}
	public List<MemberDTO> detailMember(String id) {
		String sql = "select * from Member where id=?";
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			MemberDTO member = new MemberDTO();
			member.setId(rs.getString("id"));
			member.setPassword(rs.getString("password"));
			member.setName(rs.getString("name"));
			member.setBirth(rs.getString("birth"));
			member.setGender(rs.getString("gender"));
			member.setEmail(rs.getString("email"));
			member.setBlacklist(rs.getString("blacklist"));
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
	public int DeleteMember(String id) {
			String sql="delete Member where id=?";
			int result=0;
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				result=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			finally {
				close(pstmt);
			}

			return result;
		}
	public boolean checkMember(String id,String password, MemberDTO member) {
		String sql="select * from Member where id=? and password=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setBirth(rs.getString("birth"));
				member.setGender(rs.getString("gender"));
				member.setEmail(rs.getString("email"));
				member.setBlacklist(rs.getString("blacklist"));
				return true;
			}
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		finally {
				close(pstmt);
				close(rs);
		}
		return false;
		
	}
	public boolean checkOverlap(String id) {
		String sql="select * from Member where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				rs.getString("id");
				rs.getString("password");
				rs.getString("name");
				rs.getString("birth");
				rs.getString("gender");
				rs.getString("email");
				rs.getString("blacklist");
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
	public int addBlackList(String id) {
		String sql="update Member set blacklist=? where id=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "블랙리스트");
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
	public int removeBlackList(String id) {
		String sql="update Member set blacklist=? where id=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "");
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
	public boolean blackListCheck(String id) {
		String sql="select * from Member where id=? and blacklist=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, "블랙리스트");
			rs=pstmt.executeQuery();
			if(rs.next()) {
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
	public void memberSearch(String name, String email, MemberDTO member) {
		String sql="select id from Member where name=? and email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				member.setId(rs.getString("id"));
			}
			else {
				member.setId("아이디없음");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
				close(pstmt);
				close(rs);
		}
		
	}
	public void memberSearchPassword(String id, String email, MemberDTO member) {
		String sql="select password from Member where id=? and email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				member.setPassword(rs.getString("password"));
			}
			else {
				member.setPassword("비밀번호없음");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
				close(pstmt);
				close(rs);
		}
		
	}
	public boolean checkOverlapEmail(String email) {
		String sql="select * from Member where email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				rs.getString("id");
				rs.getString("password");
				rs.getString("name");
				rs.getString("birth");
				rs.getString("gender");
				rs.getString("email");
				rs.getString("blacklist");
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
