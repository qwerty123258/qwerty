package service;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import dao.UserDAO;
import dto.UsersDTO;
import page.Paging;

public class UserService {

	public boolean create(String id, String pw, String name, String personno3, String email2) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.create(id,pw,name,personno3,email2);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
	}

	public boolean checkPersonno(String personno3) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result=dao.checkPersonno(personno3);
		if(result) {
			close(con);
			return true;
		}
		else {
			close(con);
			return false;
		}
		
		
	}

	public boolean adminCreate(String id, String pw, String name, String personno3, String email2) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.adminCreate(id,pw,name,personno3,email2);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
	}

	public boolean checkUser(String id) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result=dao.checkUser(id);
		if(result) {
			close(con);
			return true;
		}
		else {
			close(con);
			return false;
		}
		
	}

	public boolean certify(String id) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.certify(id);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
	}

	public boolean checkBlackList(String id) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result=dao.checkBlackList(id);
		if(result) {
			close(con);
			return true;
		}
		else {
			close(con);
			return false;
		}
	}

	public boolean checkCertify(String id) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result=dao.checkCertify(id);
		if(result) {
			close(con);
			return true;
		}
		else {
			close(con);
			return false;
		}
	}

	public boolean login(String id, String pw) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		boolean result=dao.login(id,pw);
		if(result) {
			close(con);
			return true;
		}
		else {
			close(con);
			return false;
		}
	}

	public int userCount() {
		int result=0;
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		result=dao.userCount();
		close(con);
		return result;
	}

	public List<UsersDTO> userList(Paging paging) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		List<UsersDTO> userList = dao.userList(paging);
		close(con);
		return userList;
	}

	public void modify(String id, UsersDTO user) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		dao.modify(id,user);
		close(con);
	}

	public boolean updateUser(String id, String pw, String name, String email) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.updateUser(id,pw,name,email);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
		
	}

	public boolean deleteUser(String id) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.deleteUser(id);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
	}

	public void detailUser(String id, UsersDTO user) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		dao.detailUser(id,user);
		close(con);
		
	}

	public boolean searchUserID(String name, String personno, UsersDTO user) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		dao.searchUserID(name,personno,user);
		if(user.getID().equals("아이디없음")) {
			close(con);
			return false;
		}
		else {
			close(con);
			return true;
		}

	}

	public boolean searchUserPw(String id, String email,UsersDTO user) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		dao.searchUserPw(id,email,user);
		if(user.getPw().equals("비밀번호없음")) {
			close(con);
			return false;
		}
		else {
			close(con);
			return true;
		}
	}

	public boolean writeAccessCheck(String id) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		String result=dao.writeAccessCheck(id);
		if(result.equals("Y")) {
			close(con);
			return true;
		}
		else {
			close(con);
			return false;
		}
	}

	public boolean permission(String id) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.permission(id);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
	}

	public int checkPonit(String id) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int point=0;
		point=dao.checkPoint(id);
		close(con);
		return point;
	}

	public void payPoint(String id, int price) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.payPoint(id,price);
		if(result>0) {
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
		}
		
	}

	public void receivePoint(String uploder, int price) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.receivePoint(uploder,price);
		if(result>0) {
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
		}
		
	}

	public boolean addBlackList(String id) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.addBlackList(id);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
	}
	
	public boolean removeBlackList(String id) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.removeBlackList(id);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
	}

	public boolean addPoint(String id,String point) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.addPoint(id,point);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
	}

	public String getGrade(String id) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		String grade=dao.getGrade(id);
		close(con);
		return grade;
	}

	public void setGrade(String id,String grade) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.setGrade(id,grade);
		if(result>0) {
			commit(con);
			close(con);
		}
		else {
			rollback(con);
			close(con);
		}
		
	}

	public boolean randomPoint(String id, int point) {
		UserDAO dao=UserDAO.getInstance();
		Connection con=getConnection();
		dao.setConnection(con);
		int result=dao.randomPoint(id,point);
		if(result>0) {
			commit(con);
			close(con);
			return true;
		}
		else {
			rollback(con);
			close(con);
			return false;
		}
	}
}
