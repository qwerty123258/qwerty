package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import dao.MemberDAO;
import dto.MemberDTO;

public class MemberSelectService {

	public List<MemberDTO> selectMember() {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		memberList=dao.selectMember();
		close(con);
		return memberList;

	}

}
