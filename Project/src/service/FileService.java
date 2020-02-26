package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.FileDAO;

public class FileService {

	public void addfile(String file, String fileoriname, String string) {
		FileDAO dao=FileDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.addfile(file,fileoriname,string);
		if(result>0) {
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
		}
		
	}

}
