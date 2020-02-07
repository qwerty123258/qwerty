package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberDetailService {

	public List<MemberDTO> detailMember(String id) {
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		memberList=dao.detailMember(id);
		close(con);
		return memberList;
		
	}

}
