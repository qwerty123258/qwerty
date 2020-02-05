package service;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import controller.Select;
import dao.DAO;

public class SelectService {
	public List<Select> selectDB() {
		DAO dao=DAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<Select> dataList = new ArrayList<Select>();
		dataList=dao.selectDB();
		close(con);
		return dataList;

	}
}
