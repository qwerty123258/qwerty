package service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import dto.SelectDTO;

public class SelectService {
	public List<SelectDTO> selectDB() {
		DAO dao=DAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<SelectDTO> dataList = new ArrayList<SelectDTO>();
		dataList=dao.selectDB();
		close(con);
		return dataList;

	}
}
