	package test1;

	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;
	import java.util.StringTokenizer;

	//게임진행 기능
	public class DBsql {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userSave = "";
		int saveMoney = 0;
		Scanner scan = new Scanner(System.in);
		boolean start=true;

		public void Connection() { //1번 커맨드 누르면 실행
			con=DBcon.DBConnection();
		}
		public void AutoCommitOff() {
			try {
				con.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void rollback() {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			
		}
		public void TurnRepeat(boolean start) throws IOException { // 1번 커맨드 누르면 실행
			int locationA = 1; // A의 위치값 저장용 변수
			int locationB = 1; // B의 위치값 저장용 변수
			String userA="A";
			String userB="B";
			this.start=start;
			int count=1;
			while (this.start) {
				System.out.println("");
				System.out.println(count+"턴");
				ACitySearch(userA);
				UserMoneySearch(userA);
				BCitySearch(userB);
				UserMoneySearch(userB);
				System.out.println("+-------+-------+--------+--------+");
				System.out.println("+   출      +   인      +   대        +   부        +");
				System.out.println("+   발      +   천      +   전        +   산        +");
				System.out.println("+-------+-------+--------+--------+");
				System.out.println("+   서      +                +   국        +");
				System.out.println("+       +                +   세        +");
				System.out.println("+   울      +                +   청        +");
				System.out.println("+-------+                +--------+");
				System.out.println("+   수      +                +   제        +");
				System.out.println("+       +                +   주        +");
				System.out.println("+   원      +                +   도        +");
				System.out.println("+-------+-------+--------+--------+");
				System.out.println("+   송      +   대      +   광        +   독        +");
				System.out.println("+   도      +   구      +   주        +   도        +");
				System.out.println("+-------+-------+--------+--------+");
			locationA=TurnA(locationA,this.start);
			if(!this.start) {
				break;
			}
			System.out.println("+-------+-------+--------+--------+");
			System.out.println("+   출      +   인      +   대        +   부        +");
			System.out.println("+   발      +   천      +   전        +   산        +");
			System.out.println("+-------+-------+--------+--------+");
			System.out.println("+   서      +                +   국        +");
			System.out.println("+       +                +   세        +");
			System.out.println("+   울      +                +   청        +");
			System.out.println("+-------+                +--------+");
			System.out.println("+   수      +                +   제        +");
			System.out.println("+       +                +   주        +");
			System.out.println("+   원      +                +   도        +");
			System.out.println("+-------+-------+--------+--------+");
			System.out.println("+   송      +   대      +   광        +   독        +");
			System.out.println("+   도      +   구      +   주        +   도        +");
			System.out.println("+-------+-------+--------+--------+");
			locationB=TurnB(locationB,this.start);
			if(!this.start) {
				break;
			}
			count++;
				}
				
			}
		public int dice() { // 주사위 값 반환하는 메서드
			int dice = (int) (Math.random() * 6) + 1;
			return dice;
		}
		public int TurnA(int locationA, boolean start) throws IOException {
			String name = "A";
			System.out.println("");
			System.out.println(name + "의 차례입니다.");
			System.out.println("주사위를 굴리시려면  아무 키워드나 입력하세요.");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine(), "");
			String turn = st.nextToken();
			int dice = dice(); // 주사위 값을 받는 변수
			locationA += dice; // 현재위치에 주사위값을 누적합
			if (locationA >= 13) { // 현재위치가 13보다 크거나 같아지는 경우
				UserMoneyBonus(name);
				locationA = locationA % 12; // 현재위치를 12로 나눈 나머지로 바꿈.ex)현위치가 11일 주사위 3이나오면 다음 위치는 14인데 14%12를 하면 현재위치는
											// 2가 됨.
				ACityproPertyCheck(locationA, "A",this.start); // @소유지권한체크메소드
				return locationA;
			} else {
				ACityproPertyCheck(locationA, "A",this.start);// @소유지권한체크메소드
			}
			return locationA;
		}
		public int TurnB(int locationB, boolean start) throws IOException {
			String othername = "B";
			System.out.println("");
			System.out.println(othername + "의 차례입니다.");
			System.out.println("주사위를 굴리시려면  아무 키워드나 입력하세요.");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine(), "");
			String turn = st.nextToken();
			int dice = dice();
			locationB += dice;
			if (locationB >= 13) {
				UserMoneyBonus(othername);
				locationB = locationB % 12;
				BCityproPertyCheck(locationB, "B",this.start);// @소유지권한체크메소드
				return locationB;
			} else {
				BCityproPertyCheck(locationB, "B",this.start);// @소유지권한체크메소드
			}
			return locationB;

		}
		public void ACityproPertyCheck(int locationA, String user, boolean start) {// 도시 소유자 확인 (주사위/계정이름)
			String sql = "SELECT CITY,PRICE,PROPERTY FROM MONOPOLY WHERE CITYNO=?";
			try {
				if (user.equals("A")) {// @
					userSave = "B";
				} else {
					userSave = "A";
				}
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, locationA);
				rs = pstmt.executeQuery();
				while (rs.next()) { // anObject=유저이름
					saveMoney=rs.getInt("price");
					if(locationA==1) {
						return;
					}
					if (locationA == 5) {
						System.out.println(rs.getString("city")+"에 방문했습니다.");
						System.out.println(saveMoney + "를 지불합니다.");
						UserMoneyTax(saveMoney, user,start);
					} 
					else if ((rs.getString("property")).equals(user)) {
						System.out.println("당신의 소유 도시인  "+rs.getString("city")+"에 방문했습니다.");
						return;
					} 
					else if ((rs.getString("property")).equals(userSave)) {// @
						System.out.print(rs.getString("city")+"에 방문했습니다.  ");
						System.out.println(rs.getString("property") + "의 소유지 입니다. " + saveMoney + "를 지불합니다.");
						// 세금메소드(rs.getInt("price"))
						UserMoneyPayment(saveMoney, user,start);
					} else {
						System.out.println(rs.getString("city")+"에 방문했습니다.");
						System.out.println("이 " +rs.getString("city")+"는 소유한 사람이없습니다.  가격: " + saveMoney);
						System.out.println("");
						System.out.println("1.구매|2.패스");// input1
						System.out.println("");
						int input1 = scan.nextInt();// 구매/패스
						switch (input1) {
						case 1:
							CityPurchase(saveMoney, user,locationA);
							break;
						case 2:
							break;

						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void BCityproPertyCheck(int locationB, String user, boolean start) {// 도시 소유자 확인 (주사위/계정이름)
			String sql = "SELECT CITY,PRICE,PROPERTY FROM MONOPOLY WHERE CITYNO=?";
			try {
				if (user.equals("B")) {// @
					userSave = "A";
				} else {
					userSave = "B";
				}
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, locationB);
				rs = pstmt.executeQuery();
				while (rs.next()) { // anObject=유저이름
					saveMoney=rs.getInt("price");
					if(locationB==1) {
						return;
					}
					if (locationB == 5) {
						System.out.println(rs.getString("city")+"에 방문했습니다.");
						System.out.println(saveMoney + "를 지불합니다.");
						UserMoneyTax(saveMoney, user,start);
					} else if ((rs.getString("property")).equals(user)) {
						System.out.println("당신의 소유 도시인  "+rs.getString("city")+"에 방문했습니다.");
					} 
					else if((rs.getString("property")).equals(userSave)) {// @
						System.out.print(rs.getString("city")+"에 방문했습니다.  ");
						System.out.println(rs.getString("property") + "의 소유지 입니다. " + saveMoney + "를 지불합니다.");
						// 세금메소드(rs.getInt("price"))
						UserMoneyPayment(saveMoney, user,start);
					} else {
						System.out.println(rs.getString("city")+"에 방문했습니다.");
						System.out.println("이 " +rs.getString("city")+"는 소유한 사람이없습니다.  가격: " + saveMoney);
						System.out.println("");
						System.out.println("1.구매|2.패스");// input1
						System.out.println("");
						int input1 = scan.nextInt();// 구매/패스
						switch (input1) {
						case 1:
							CityPurchase(saveMoney, user,locationB);
							break;
						case 2:
							break;

						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void UserMoneyPayment(int price, String user, boolean start) {// 지불 메소드(가격,지불하는계정)
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					saveMoney = rs.getInt("money");
				}
				if (saveMoney <= price) {// 해당잔액보다 지불금액이 높거나 같다면파산
					System.out.println("파산하셨습니다");
					System.out.println("");
					System.out.println("");
					System.out.println("게임종료");
					System.out.println("");
					System.out.println("");
					this.start=false;
				} 
				else {

					sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, user);// ""=호출받은 유저이름 넣는곳
					pstmt.executeUpdate();
					if (user.equals("A")) {
						userSave = "B";
					} else {
						userSave = "A";
					}
					sql = "UPDATE USERLIST SET MONEY=MONEY+? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, userSave);// 상대유저 넣는곳
					pstmt.executeUpdate();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void UserMoneyBonus(String user) {// 출발지점오면 보너스 200
			String sql = "UPDATE USERLIST SET MONEY=MONEY+? WHERE NAME=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, 200);
				pstmt.setString(2, user);
				pstmt.executeUpdate();
				System.out.println("출발지점을 만나 보너스를 받습니다.");
				System.out.println("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void UserMoneyTax(int price, String user, boolean start) {// 세금 메소드(가격,지불하는계정)
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					saveMoney = rs.getInt("money");
				}
				if (saveMoney <= price) {// 해당잔액보다 지불금액이 높거나 같다면파산
					System.out.println("파산하셨습니다");
					System.out.println("");
					System.out.println("");
					System.out.println("게임종료");
					System.out.println("");
					System.out.println("");
					this.start=false;

				} else {

					sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, user);// ""=호출받은 유저이름 넣는곳
					pstmt.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	//게임진행중에  보유도시, 보유잔액 보려고만듬 
		public void ACitySearch(String user) {
			String sql = "SELECT * FROM MONOPOLY WHERE PROPERTY=?";
			try {// 도시보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				System.out.print(user+"의"+"소유도시 : ");
				while (rs.next()) {
					System.out.print(rs.getString("city")+" ");
				}
				System.out.println("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void BCitySearch(String user) {
			String sql = "SELECT * FROM MONOPOLY WHERE PROPERTY=?";
			try {// 도시보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				System.out.print(user+"의"+"소유도시 : ");
				while (rs.next()) {
					System.out.print(rs.getString("city")+" ");
				}
				System.out.println("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void UserMoneySearch(String user) {
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try {// 잔액보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(user + "님의 남은잔액: " + rs.getInt("money"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void CityPurchase(int price, String user,int locationA) {// 공백도시 구매(가격,구매하는계정)
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try { // ""=호출받은 유저이름 넣는곳
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					saveMoney = rs.getInt("money");
				}
				if (saveMoney <= price) {
					System.out.println("잔액이 부족합니다");
				}
				if(saveMoney > price) {
					sql = "select * from monopoly where cityno=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, locationA);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						System.out.println(user+"가 "+ rs.getString("city")+"를 구매하였습니다.");
					}
					sql = "UPDATE MONOPOLY SET PROPERTY=? WHERE PRICE=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, user);
					pstmt.setInt(2, price);
					pstmt.executeUpdate();
					sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, user);
					pstmt.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}	
	}