package service;

import java.sql.*;

import dao.DAO;
import static db.JdbcUtil.*;

public class InputService {

	public boolean inputDB(String data1) {
		DAO dao=DAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.inputDB(data1);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
		}
		close(con);
		return false;
		
	}

}
