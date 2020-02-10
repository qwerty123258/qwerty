package service;
import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.BoardDAO;
import dto.BoardDTO;

public class WriteService {

	public boolean writeBoard(BoardDTO board) {
		BoardDAO dao=BoardDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.writeBoard(board);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
		
	}

}
