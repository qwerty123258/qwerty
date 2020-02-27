package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.FileDTO;

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
	public List<FileDTO> fileDetail(String bno) {
		String sql="select * from bfiles where bno=?";
		List<FileDTO> fileList = new ArrayList<FileDTO>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				FileDTO file = new FileDTO();
				file.setBfno(rs.getString("bfno"));
				file.setBfile(rs.getString("bfile"));
				file.setBoriginfile(rs.getString("boriginfile"));
				file.setPrice(rs.getString("price"));
				file.setBno(rs.getString("bno"));
				fileList.add(file);
			}
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
		return fileList;
	}
	public int updateFile(String file, String fileoriname, String string, String string2) {
		String sql="update bfiles set bfile=?,boriginfile=?,price=? where bfno=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, file);
			pstmt.setString(2, fileoriname);
			pstmt.setString(3, string);
			pstmt.setString(4, string2);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public List<FileDTO> fileList(String bno) {
		List<FileDTO> fileList = new ArrayList<FileDTO>();
		String sql="select * from bfiles where bno=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				FileDTO file = new FileDTO();
				file.setBfno(rs.getString("bfno"));
				file.setBfile(rs.getString("bfile"));
				file.setBoriginfile(rs.getString("boriginfile"));
				file.setPrice(rs.getString("price"));
				fileList.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return fileList;
	}
}
