package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.UsersDTO;
import page.Paging;

public class UserDAO {
	private static UserDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static UserDAO getInstance() {
		//싱글톤 방식 : 객체를 한번만 생성후 상주시켜 메모리 소모를 줄여줌.
		if(dao==null) {
			dao=new UserDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int adminCreate(String id, String pw, String name, String personno3, String email2) {
		String sql = "insert into users values(?,?,?,?,?,'N','Y',1000,'ADMIN','Y')";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, personno3);
			pstmt.setString(5, email2);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int create(String id, String pw, String name, String personno3, String email2) {
		String sql = "insert into users values(?,?,?,?,?,'N','N',1000,'BRONZE','N')";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, personno3);
			pstmt.setString(5, email2);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public boolean checkPersonno(String personno3) {
		String sql = "select * from Users where personno=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, personno3);
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
	public boolean checkUser(String id) {
		String sql = "select * from Users where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
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
	public int certify(String id) {
		String sql = "update users set certify='Y' where id=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public boolean checkBlackList(String id) {
		String sql = "select * from Users where id=?";
		String blackList="";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				blackList=rs.getString("BLACKLIST");
				if(blackList.equals("Y")) {
					return true;
				}
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
	public boolean checkCertify(String id) {
		String sql = "select * from Users where id=?";
		String certify="";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				certify=rs.getString("certify");
				if(certify.equals("Y")) {
					return true;
				}
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
	public boolean login(String id, String pw) {
		String sql = "select * from Users where id=? and pw=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
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
	public int userCount() {
		String sql = "select count(*) as count from Users";
		int count=0;
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
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
	public List<UsersDTO> userList(Paging paging) {
	    int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
        List<UsersDTO> userList = new ArrayList<UsersDTO>();
        String sql = "SELECT u.* FROM  (select ROWNUM row_num,Users.* from Users) u  WHERE row_num >= ? and row_num <= ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs=pstmt.executeQuery();
            while(rs.next()){
            	UsersDTO user = new UsersDTO();
            	user.setID(rs.getString("id"));
            	user.setPw(rs.getString("pw"));
            	user.setName(rs.getString("name"));
            	user.setPersonno(rs.getString("personno"));
            	user.setEmail(rs.getString("email"));
            	user.setPoint(rs.getString("point"));
            	user.setGrade(rs.getString("grade"));
            	user.setCertify(rs.getString("certify"));
            	user.setBfile(rs.getString("bfile"));
            	user.setBlacklist(rs.getString("blacklist"));
            	userList.add(user);
            }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return userList;
	}
	public void modify(String id, UsersDTO user) {
        String sql = "select * from users where id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
            while(rs.next()){
            	user.setID(rs.getString("id"));
            	user.setName(rs.getString("name"));
            	user.setEmail(rs.getString("email"));
            }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
	}
	public int updateUser(String id, String pw, String name, String email) {
		String sql = "update users set pw=?,name=?,email=? where id=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
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
	public int deleteUser(String id) {
		String sql = "delete users where id=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public void detailUser(String id, UsersDTO user) {
		String sql = "select * from users where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
            	user.setID(rs.getString("id"));
            	user.setPw(rs.getString("pw"));
            	user.setName(rs.getString("name"));
            	user.setPersonno(rs.getString("personno"));
            	user.setEmail(rs.getString("email"));
            	user.setPoint(rs.getString("point"));
            	user.setGrade(rs.getString("grade"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
	}
	public void searchUserID(String name, String personno, UsersDTO user) {
		String sql = "select * from users where name=? and personno=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, personno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				user.setID(rs.getString("id"));		
			}
			else {
				user.setID("아이디없음");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
	}
	public void searchUserPw(String id, String email, UsersDTO user) {
		String sql = "select * from users where id=? and email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				user.setPw(rs.getString("pw"));
			}
			else {
				user.setPw("비밀번호없음");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		
	}
	public String writeAccessCheck(String id) {
		String sql = "select * from users where id=?";
		String result="N";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result= rs.getString("bfile");
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
	public int permission(String id) {
		String sql = "update users set bfile='Y' where id=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int checkPoint(String id) {
		String sql="select * from users where id=?";
		int point=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				point=rs.getInt("point");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return point;
	}
	public int payPoint(String id, int price) {
		int result=0;
		String sql="update users set point=point-? where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, price);
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
	public int receivePoint(String uploder, int price) {
		int result=0;
		String sql="update users set point=point+? where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, price);
			pstmt.setString(2, uploder);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		
		return result;
	}
	public int addBlackList(String id) {
		int result=0;
		String sql="update users set blacklist='Y' where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
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
		int result=0;
		String sql="update users set blacklist='N' where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		
		return result;
	}
	public int addPoint(String id,String point) {
		int result=0;
		String sql="update users set point=point+? where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, point);
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
	public String getGrade(String id) {
		String sql="select * from users where id=?";
		String grade=null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
			grade=rs.getString("grade");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return grade;
	}
	public int setGrade(String id,String grade) {
		String sql="update users set grade=? where id=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, grade);
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
	public int randomPoint(String id, int point) {
		String sql="update users set point=point+? where id=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, point);
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
