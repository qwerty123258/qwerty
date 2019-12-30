package test1;
import java.sql.*;
import java.util.*;

public class Sql {
	Connection con = null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public void Connection() {
		con=DBcon.DBConnection();
	}
	public boolean log(String id, String password,List<Member> memberList) {
		for(int i=0; i<memberList.size(); i++) {
			if(memberList.get(i).getId().equals(id) && memberList.get(i).getPassword().equals(password)) {//리스트에 저장된 ID,비밀번호 비교문
				return true; //리스트에 저장되어있는 ID가 있으면
			}
		}
		System.out.println("해당하는 회원정보가 없습니다. 다시 입력하세요.");
		return false; //없으면
	}
	public void Update(String id, String password) {
		Scanner scan = new Scanner(System.in);
		String sql = "update member set password=?,name=?,email=?,phone=? where id=? and password=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(5,id);
				pstmt.setString(6,password);
				System.out.println("변경하려는 비밀번호로 입력하세요.");
				pstmt.setString(1, scan.next());
				System.out.println("변경하려는 이름으로 입력하세요.");
				pstmt.setString(2, scan.next());
				System.out.println("변경하려는 이메일로 입력하세요.");
				pstmt.setString(3, scan.next());
				System.out.println("변경하려는 연락처로 입력하세요.");
				pstmt.setString(4, scan.next());
				pstmt.executeUpdate();
				System.out.println("회원 정보 변경 완료");
			} catch (SQLException e) {
				System.out.println("DB접속 실패");
				e.printStackTrace();
			}	
		
	}

	public void Delete(String id, String password) {
		Scanner scan = new Scanner(System.in);
		String sql = "delete from member where id=? and password=?";
			try{
				pstmt = con.prepareStatement(sql); 
				pstmt.setString(1,id);
				pstmt.setString(2,password);
				pstmt.executeUpdate();
				System.out.println("회원 탈퇴 완료");
			} catch (SQLException e) {
				System.out.println("DB 접속실패");
				e.printStackTrace();
			}	
	
	}
	public void SignUp() {
		Scanner scan = new Scanner(System.in);
		String sql = "insert into member values(?,?,?,?,?,?,?,sysdate)";
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println("ID");
			pstmt.setString(1,scan.next());
			System.out.println("비밀번호");
			pstmt.setString(2, scan.next());
			System.out.println("이름");
			pstmt.setString(3, scan.next());
			System.out.println("생년월일");
			pstmt.setString(4, scan.next());
			System.out.println("성별");
			pstmt.setString(5, scan.next());
			System.out.println("이메일");
			pstmt.setString(6, scan.next());
			System.out.println("연락처");
			pstmt.setString(7, scan.next());
			pstmt.executeUpdate();
}
		catch(Exception e) {
			System.out.println("DB접속 실패");
		}
	}
	public List<Member> List() {
		String sql = "select * from member order by day asc"; //생성날짜를 기준으로 오름차순 , 그래야 관리자 파악가능
		List<Member> memberList = new ArrayList<Member>();
		try {
			pstmt = con.prepareStatement(sql); 
			rs=pstmt.executeQuery(); 
			while(rs.next()) {
				Member mem= new Member();
						mem.setId(rs.getString("id"));
						mem.setPassword(rs.getString("password"));
						mem.setName(rs.getString("name"));
						mem.setBirth(rs.getString("birth"));
						mem.setGender(rs.getString("gender"));
						mem.setEmail(rs.getString("email"));
						mem.setPhone(rs.getString("phone"));
						mem.setDay(rs.getString("day"));
						memberList.add(mem);
					}
	}
		 catch(SQLException e) {
			System.out.println("DB접속 실패");
			e.printStackTrace();
		}
		return memberList;
	}
	public void Admin(List<Member> memberList) {
		Scanner scan = new Scanner(System.in);
		System.out.println("관리자 ID를 입력하세요.");
		String adminid=scan.next();
		System.out.println("관리자 비밀번호를 입력하세요.");
		String adminpassword=scan.next();
		if(memberList.get(0).getId().equals(adminid) && memberList.get(0).getPassword().equals(adminpassword)) {
			//리스트에 저장된 관리자 ID,비밀번호 비교문, 가장 처음에 등록된 사람이 관리자라고 전제를 깔아둠.
			for(int i=0; i<memberList.size(); i++) {
			System.out.println(memberList.get(i).toString());
			}
		}
		else {
			System.out.println("관리자 정보가 맞지 않습니다.");
		}
		
	}
	public void UpdateMain(List<Member> memberList) {
		Scanner scan = new Scanner(System.in);
		System.out.println("본인확인을 위한 ID를 입력해주세요.");
		String id = scan.next();
		System.out.println("본인확인을 위한 비밀번호를 입력해주세요.");
		String password = scan.next();
		if(log(id,password,memberList)) { //log에서 반환받은 메서드가 참이면
				Update(id,password); //데이터를 수정하는
	}
		
	}
	public void DeleteMain(List<Member> memberList) {
		Scanner scan = new Scanner(System.in);
		System.out.println("본인확인을 위한 ID를 입력해주세요.");
		String id = scan.next();
		System.out.println("본인확인을 위한 비밀번호를 입력해주세요.");
		String password = scan.next();
		if(log(id,password,memberList)) { //log에서 반환받은 메서드가 참이면
				Delete(id,password); //데이터를 지우는 메서드
	}
		
	}
	}
