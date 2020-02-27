package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.FileDAO;
import dto.FileDTO;

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

	public List<FileDTO> fileDetail(String bno) {
		FileDAO dao=FileDAO.getInstance();
		List<FileDTO> fileList=new ArrayList<FileDTO>();
		Connection con=getConnection();
		dao.setConnection(con);
		fileList=dao.fileDetail(bno);
		close(con);
		return fileList;
		
	}

	public void updateFile(String file, String fileoriname, String string, String string2) {
		FileDAO dao=FileDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.updateFile(file,fileoriname,string,string2);
		if(result>0) {
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
		}
	}

	public List<FileDTO> fileList(String bno) {
		List<FileDTO> fileList = new ArrayList<FileDTO>();
		FileDAO dao=FileDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		fileList=dao.fileList(bno);
		return fileList;
	}

}
