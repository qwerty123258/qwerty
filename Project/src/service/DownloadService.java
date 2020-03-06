package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.DownloadDAO;
import dto.DownloadDTO;

public class DownloadService {

	public void addHistory(String id, String bfno, String boriginfile, String bno) {
		DownloadDAO dao=DownloadDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.addHistory(id,bfno,boriginfile,bno);
		if(result>0) {
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
		}
		
	}

	public boolean checkHistory(String id, String bfno) {
		DownloadDAO dao=DownloadDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result=dao.checkHistory(id,bfno);
		if(result) {
			close(con);
			return true;
		}
		else {
			close(con);
			return false;
		}
		
	}

	public List<DownloadDTO> downloadList(String id) {
		DownloadDAO dao=DownloadDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<DownloadDTO> downloadList=new ArrayList<DownloadDTO>();
		downloadList=dao.downloadList(id);
		close(con);
		return downloadList;
	}

	public void updateHistory(String id, String bfno, String boriginfile) {
		DownloadDAO dao=DownloadDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.updateHistory(id,bfno,boriginfile);
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
