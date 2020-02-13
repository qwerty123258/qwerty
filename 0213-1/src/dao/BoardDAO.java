package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static db.JdbcUtil.*;

import dto.BoardDTO;
import page.Paging;

public class BoardDAO {
	private static BoardDAO dao;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static BoardDAO getInstance() {
		//싱글톤 방식 : 객체를 한번만 생성후 상주시켜 메모리 소모를 줄여줌.
		if(dao==null) {
			dao=new BoardDAO();
		}
		return dao;
	}
	public void setConnection(Connection con) {
		this.con=con;
	}
	public int writeBoard(BoardDTO board) {
		String sql="insert into Board values(board_seq.nextval,?,?,?,sysdate,?,0,?,?)";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getBcontent());
			pstmt.setString(4, board.getBpassword());
			pstmt.setString(5, board.getBimgfile());
			pstmt.setString(6, board.getBfile());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
		
	}
	public void detail(String bnum,BoardDTO board) {
		String sql="select * from Board where bnum=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bnum);
			rs=pstmt.executeQuery();
			while(rs.next()){
				board.setBnum(rs.getInt("bnum"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setWritedate(rs.getString("writedate"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBview(rs.getInt("bview"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBimgfile(rs.getString("bimgfile"));
				board.setBfile(rs.getString("bfile"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
			close(rs);
		}
	}
	public int bViewIncrease(String bnum) {
		String sql="update Board set bview=bview+? where bnum=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, bnum);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
		
	}
	public int updateBoard(String bnum, String title, String bcontent, String bimgfile, String bfile) {
		String sql="update Board set title=?,bcontent=?,writedate=sysdate,bimgfile=?,bfile=? where bnum=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, bcontent);
			pstmt.setString(3, bimgfile);
			pstmt.setString(4, bfile);
			pstmt.setString(5, bnum);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
	}
	public int deleteBoard(String bnum) {
		String sql="delete Board where bnum=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, bnum);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
		
	}
	public List<BoardDTO> BoardList(Paging paging){        
	    int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();      
        String sql = "SELECT b.*,to_char(writedate,'YYYY-MM-DD HH:MM') as bdate FROM  (select ROWNUM row_num,board.* from board) b  WHERE row_num >= ? and row_num <= ?";
        List<BoardDTO> boardList = new ArrayList<BoardDTO>();
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startNum);
            pstmt.setInt(2, endNum);
            rs = pstmt.executeQuery();
            while(rs.next()){
				BoardDTO board=new BoardDTO();
				board.setBnum(rs.getInt("bnum"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setWritedate(rs.getString("bdate"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBview(rs.getInt("bview"));
				board.setBimgfile(rs.getString("bimgfile"));
				board.setBfile(rs.getString("bfile"));
				boardList.add(board);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
			close(pstmt);
			close(rs);
        }
        return boardList;
    }
	
	public List<BoardDTO> BoardListOrder(Paging paging){        
	    int startNum = paging.getStartNum();
	    int endNum = paging.getEndNum();      
        String sql = "SELECT b.*,to_char(writedate,'YYYY-MM-DD HH:MM') as bdate "
        		+ "FROM (select ROWNUM row_num,board.* "
        		+ "from (select * from board order by bview desc) board"
        		+ ") b  WHERE row_num >= ? and row_num <= ?";
        List<BoardDTO> boardList = new ArrayList<BoardDTO>();
        try{
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startNum);
            pstmt.setInt(2, endNum);
            rs = pstmt.executeQuery();
            while(rs.next()){
				BoardDTO board=new BoardDTO();
				board.setBnum(rs.getInt("bnum"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setWritedate(rs.getString("bdate"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBview(rs.getInt("bview"));
				board.setBimgfile(rs.getString("bimgfile"));
				board.setBfile(rs.getString("bfile"));
				boardList.add(board);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
			close(pstmt);
			close(rs);
        }
        return boardList;
    }
	public int getAllCount() {
	    String sql = "SELECT COUNT(*) as count FROM Board";
	    int count = 0;
	    try{
	    	pstmt = con.prepareStatement(sql);
	        rs= pstmt.executeQuery();
	        if(rs.next()){
	            count = rs.getInt("count");
	        }
	    }catch(SQLException e){
	        e.printStackTrace();
	    }finally{
			close(pstmt);
			close(rs);
	    }
	    return count;
	 }
}
