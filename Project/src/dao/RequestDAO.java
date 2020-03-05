package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.RequestDTO;
import page.Paging;

public class RequestDAO {
	private static RequestDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static RequestDAO getInstance() {
		if(dao==null) {
			dao=new RequestDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int writeBoard(String title, String content, String id) {
		String sql = "insert into requests values(request_seq.nextval,?,?,?,SYSDATE)";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, id);
			pstmt.setString(3, content);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int countRequest() {
		String sql = "select count(*) as count from requests";
		int count=0;
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
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
	public List<RequestDTO> requestList(Paging paging) {
		List<RequestDTO> requestList = new ArrayList<RequestDTO>();
	    int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();
        String sql = "SELECT r.*,to_char(writedate,'YYYY-MM-DD HH:MM') as rdate FROM  (select ROWNUM row_num,requests.* from requests) r  WHERE row_num >= ? and row_num <= ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				RequestDTO request = new RequestDTO();
				request.setRno(rs.getString("rno"));
				request.setRtitle(rs.getString("rtitle"));
				request.setId(rs.getString("id"));
				request.setWritedate(rs.getString("rdate"));
				requestList.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		return requestList;
	}
	public void requestDetail(String rno, RequestDTO req) {
		String sql = "select * from requests where rno=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				req.setRno(rs.getString("rno"));
				req.setRtitle(rs.getString("rtitle"));
				req.setId(rs.getString("id"));
				req.setWritedate(rs.getString("writedate"));
				req.setRcontent(rs.getString("rcontents"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
		
	}
	public int deletereq(String rno) {
		int result=0;
		String sql = "delete requests where rno=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rno);
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
