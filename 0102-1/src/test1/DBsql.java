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
		String userSave = "", countUser = "";
		int saveMoney = 0,saveMoney2 = 0;
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

		public void TurnRepeat(boolean start) throws IOException { // 1번 커맨드 누르면 실행
			int locationA = 1; // A의 위치값 저장용 변수
			int locationB = 1; // B의 위치값 저장용 변수
			String userA="A";
			String userB="B";
			this.start=start;
			while (this.start) {
			locationA=TurnA(locationA,this.start);
			if(!this.start) {
				break;
			}
			locationA=TurnB(locationB,this.start);
			if(!this.start) {
				break;
			}
				}
				
			}

		public int dice() { // 주사위 값 반환하는 메서드
			int dice = (int) (Math.random() * 6) + 1;
			return dice;
		}

		public int TurnA(int locationA, boolean start) throws IOException {
			String name = "A";
			System.out.println(name + "의 차례입니다.");
			citySelect(name);
			moneySelect(name);
			System.out.println("주사위를 굴리시려면  아무 키워드나 입력하세요.");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine(), "");
			String turn = st.nextToken();
			int dice = dice(); // 주사위 값을 받는 변수
			System.out.println(name + "의 주사위 값은 " + dice);
			locationA += dice; // 현재위치에 주사위값을 누적합
			if (locationA >= 13) { // 현재위치가 13보다 크거나 같아지는 경우
				locationA = locationA % 12; // 현재위치를 12로 나눈 나머지로 바꿈.ex)현위치가 11일 주사위 3이나오면 다음 위치는 14인데 14%12를 하면 현재위치는
											// 2가 됨.
				System.out.println(dice + "칸 앞으로!!" + name + "의 위치는 " + locationA + "\n"); // 움직인다는 알림용 출력 @수정
				proPertyCheck(locationA, "A",this.start); // @소유지권한체크메소드
				return locationA;
			} else {
				System.out.println(dice + "칸 앞으로!!" + name + "의 위치는 " + locationA + "\n");
				proPertyCheck(locationA, "A",this.start);// @소유지권한체크메소드
			}
			return locationA;
		}

		public int TurnB(int locationB, boolean start) throws IOException {
			String othername = "B";
			System.out.println(othername + "의 차례입니다.");
			citySelect(othername);
			moneySelect(othername);
			System.out.println("주사위를 굴리시려면  아무 키워드나 입력하세요.");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine(), "");
			String turn = st.nextToken();
			int dice = dice();
			System.out.println(othername + "의 주사위 값은 " + dice());
			locationB += dice;
			if (locationB >= 13) {
				locationB = locationB % 12;
				System.out.println(dice + "칸 앞으로!!" + othername + "의 위치는 " + locationB + "\n");
				proPertyCheck(locationB, "B",this.start);// @소유지권한체크메소드
				return locationB;
			} else {
				System.out.println(dice + "칸 앞으로!!" + othername + "의 위치는 " + locationB + "\n");
				proPertyCheck(locationB, "B",this.start);// @소유지권한체크메소드
			}
			return locationB;

		}

		public void proPertyCheck(int dice, String user, boolean start) {// 도시 소유자 확인 (주사위/계정이름)
			String sql = "SELECT CITY,PRICE,PROPERTY FROM MONOPOLY WHERE CITYNO=?";
			try {
				if (user.equals("A")) {// @
					userSave = "B";
				} else {
					userSave = "A";
				}

				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dice);
				rs = pstmt.executeQuery();
				while (rs.next()) { // anObject=유저이름
					System.out.println("지금 위치는 " + rs.getString("city"));
					saveMoney2=rs.getInt("price");
					if (dice == 0) {
						bonus(user);
					} else if (dice == 5) {
						
						System.out.println(saveMoney2 + "를 지불합니다.");
						tax(saveMoney2, user,start);
					} else if ((rs.getString("property")).equals(user)) {
						System.out.println("당신의 소유지입니다");
					} else if ((rs.getString("property")).equals(userSave)) {// @

						System.out.println(rs.getString("property") + "의 소유지 입니다" + saveMoney2 + "를 지불합니다.");
						// 세금메소드(rs.getInt("price"))
						payment(saveMoney2, user,start);
					} else {
						System.out.println("이 도시는 소유한 사람이없습니다.  가격: " + saveMoney2);
						System.out.println("1.구매|2.패스");// input1
						int input1 = scan.nextInt();// 구매/패스
						switch (input1) {
						case 1:
							cityPurchase(saveMoney2, user);
							moneySelects(user);
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

		public void cityPurchase(int price, String user) {// 공백도시 구매(가격,구매하는계정)
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
				} else {
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

		public void payment(int price, String user, boolean start) {// 지불 메소드(가격,지불하는계정)
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
					this.start=false;
				} 
				else {

					sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, user);// ""=호출받은 유저이름 넣는곳
					pstmt.executeUpdate();
					if (user.equals("A")) {
						countUser = "B";
					} else {
						countUser = "A";
					}
					sql = "UPDATE USERLIST SET MONEY=MONEY+? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, countUser);// 상대유저 넣는곳
					pstmt.executeUpdate();
					moneySelects(user);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void bonus(String user) {// 출발지점오면 보너스 200
			String sql = "UPDATE USERLIST SET MONEY=MONEY+? WHERE NAME=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, 200);
				pstmt.setString(2, user);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	//게임진행중에  보유도시, 보유잔액 보려고만듬 
		public void citySelect(String user) {
			String sql = "SELECT CITY FROM MONOPOLY WHERE PROPERTY=?";
			try {// 도시보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				System.out.print("소유도시: ");
				while (rs.next()) {
					System.out.print(rs.getString("city"));
					System.out.print(" ");
				}
				System.out.println("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void moneySelects(String user) {
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try {// 잔액보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(user + "님의 남은잔액: " + rs.getInt("money"));
				}
				System.out.println("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void moneySelect(String user) {
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try {// 잔액보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(user + "님의 잔액: " + rs.getInt("money"));
				}
				System.out.println("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void tax(int price, String user, boolean start) {// 세금 메소드(가격,지불하는계정)
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
					this.start=false;

				} else {

					sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, user);// ""=호출받은 유저이름 넣는곳
					pstmt.executeUpdate();
					moneySelects(user);
				}
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
	}