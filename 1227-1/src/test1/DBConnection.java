package test1;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class DBConnection {
		public static Connection MakeConnection() { //리턴 타입은 Connection(java.sql.Connection 인터페이스)
			Connection con = null;
			String user = "sj1226"; //연동할 DB의 계정정보 저장
			String password="1234"; //연동할 DB의 계정정보 저장
			String url="jdbc:oracle:thin:@localhost:1521:xe"; //연동할 DB의 위치 저장. 만약 다른 컴이면 IP주소 필요.
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection(url, user, password);
					
				
			} catch (ClassNotFoundException e) {
				System.out.println("DB 드라이버 로딩실패");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("DB 접속실패");
				e.printStackTrace();
			}
			System.out.println("접속 성공");
			
			return con;
			
		}
}