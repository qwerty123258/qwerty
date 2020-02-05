package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import static db.JdbcUtil.*;

public class InputService {

	public boolean inputDB(String data1, String data2) {
//		DAO 객체를 getInstance 메소드로 받아옴
//		JdbcUtil로부터 Connection 객체를 받아옴
//		DAO 클래스에 접속 완료된 정보 전달
//		DAO 클래스의 insert 수행 메소드 호출
		DAO dao=DAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.inputDB(data1,data2);
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

	public List<String> selectDB() {
		DAO dao=DAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<String> dataList = new ArrayList<String>();
		dataList=dao.selectDB();
		close(con);
		return dataList;

	}

}
