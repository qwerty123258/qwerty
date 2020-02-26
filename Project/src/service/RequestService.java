package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.RequestDAO;
import dto.RequestDTO;
import page.Paging;

public class RequestService {
	public void writeBoard(String title, String content, String id) {
		RequestDAO dao=RequestDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.writeBoard(title,content,id);
		if(result>0) {
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
		}
		
	}

	public int countRequest() {
		int count=0;
		RequestDAO dao=RequestDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		count=dao.countRequest();
		close(con);
		return count;
	}

	public List<RequestDTO> requestList(Paging paging) {
		RequestDAO dao=RequestDAO.getInstance();
		List<RequestDTO> requestList=new ArrayList<RequestDTO>();
		Connection con=getConnection();
		dao.setConnection(con);
		requestList=dao.requestList(paging);
		return requestList;
	}

	public void requestDetail(String rno, RequestDTO req) {
		RequestDAO dao=RequestDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		dao.requestDetail(rno,req);
		close(con);
		
	}
}
