package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberDetailPopUpService {

	public List<MemberDTO> DetailPopUp(String id) {
		List<MemberDTO> memberList=new ArrayList<MemberDTO>();
		MemberDAO dao=MemberDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		memberList=dao.DetailPopUp(id);
		close(con);
		return memberList;
		
	}

}
