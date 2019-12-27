package test1;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;

	public class DBsql {
		Connection con=null; //접속을 위한 변수
		PreparedStatement pstmt = null; // 쿼리문 전송을 위한 변수
		ResultSet rs=null; //select 결과를 저장하기 위한 변수.
		
		public void dbConnection() {
			con =DBConnection.MakeConnection();
		}

		public void selectDB() {
			String sql = "select * from student"; //쿼리문 저장용 변수, 가독성 위함
			try {
				pstmt = con.prepareStatement(sql); //DB에서 쿼리문을 써놓고 실행하기 바로 그 직전인 상태
				rs=pstmt.executeQuery(); //입력하고 실행한 상태.
				while(rs.next()) {
					System.out.print(rs.getInt("studentno")+" ");
					System.out.print(rs.getString("name")+" ");
					System.out.print(rs.getInt("age")+" ");
					System.out.print(rs.getString("address")+" ");
					System.out.print(rs.getString("gender")+" ");
					System.out.print(rs.getString("phone")+"\n");
				}
			} catch (SQLException e) {
				System.out.println("DB접속 실패");
				e.printStackTrace();
			}
		}
		public void insertDB() {
			Scanner scan = new Scanner(System.in);
			String sql = "insert into STUDENT values(?,?,?,?,?,?)";//쿼리문 저장용 변수, 틀만 저장해둠
			try {
				pstmt = con.prepareStatement(sql); //DB에서 쿼리문을 써놓고 실행하기 바로 그 직전인 상태
				System.out.println("학생번호");
				pstmt.setInt(1,scan.nextInt());
				System.out.println("이름");
				pstmt.setString(2, scan.next());
				System.out.println("나이");
				pstmt.setInt(3, scan.nextInt());
				System.out.println("주소");
				pstmt.setString(4, scan.next());
				System.out.println("성별");
				pstmt.setString(5, scan.next());
				System.out.println("연락처");
				pstmt.setString(6, scan.next());
				// 틀만 입력해두었기때문에 세부 데이터를 입력.
				pstmt.executeUpdate(); //입력하고 실행한 상태.
			} catch (SQLException e) {
				System.out.println("DB접속 실패");
				e.printStackTrace();
			}
		}
		public void editDB() {
			String sql = "update student set phone=? where studentno=?"; //쿼리문 저장용 변수, 가독성 위함
			Scanner scan = new Scanner(System.in);
			try {
				pstmt = con.prepareStatement(sql); //DB에서 쿼리문을 써놓고 실행하기 바로 그 직전인 상태
				System.out.println("수정할 학생의 학생번호");
				pstmt.setString(2, scan.nextLine());
				System.out.println("수정할 전화번호");
				pstmt.setString(1, scan.nextLine());
				pstmt.executeUpdate(); //입력하고 실행한 상태.
			} catch (SQLException e) {
				System.out.println("DB접속 실패");
				e.printStackTrace();
			}
		}
}
