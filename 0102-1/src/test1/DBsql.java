		package test1;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;
	public class DBsql {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userSave = "";
		int saveMoney = 0;
		Scanner scan = new Scanner(System.in);
		boolean start=true;
		
		public void Connection() { //1번 커맨드 누르면 실행, DB와 연결해주는 메소드
			con=DBcon.DBConnection();
		}
		public void AutoCommitOff() { //1먼 커맨드 누르면 실행, 이미 저장되아있는 DB를 건들지 않기위해 자동커밋기능을 해제하는 메소드
			try {
				System.out.println("자동 커밋 해제");
				con.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void map() { //맵을 보여주는 메소드
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
		}
		public int dice() { // 주사위를 굴려서 값을 반환하는 메소드
			int dice = (int) (Math.random() * 6) + 1;
			return dice;
		}
		public void TurnRepeat() { // 2번 커맨드 누르면 실행이며 반복됨.
			String userA=firstMemberSearch(); //USER A 이름 변수
			String userB=secondMemberSearch(); //USER B 이름 변수
			int locationA = 1; // A의 위치값 저장용 변수
			int locationB = 1; // B의 위치값 저장용 변수
			int count=1; //몇턴을 진행하였는지 저장하기 위한 변수
			this.start=true; //이 클래스의 boolean 필드 값을 true로 설정한다. >>게임이 한 판 끝난 뒤에는 해당 필드의 값이 false인데 이를 다시 true로 바꿔주며 다시 게임이 시작되게 한다.
			while (this.start) { //현재 필드의 값이 true이면
				map();
				System.out.println("");
				System.out.println(count+"턴");
				ACitySearch(userA);
				UserMoneySearchToast(userA);
				BCitySearch(userB);
				UserMoneySearchToast(userB);
			locationA=TurnA(locationA,userA,userB); //A의턴을 진행.
			if(!this.start) { //현재 필드의 값이 false이면 게임이 끝난것이므로
				rollback(); //값이 바뀌어버린 DB값을 롤백
				WinScoreInsertDB(userB); //이긴 유저에게 스코어 추가
				LoseScoreInsertDB(userA); // 진 유저에게 스코어 추가
				DisConnection(); //접속을 종료함.
				break; //반복 종료.
			}
			map();
			locationB=TurnB(locationB,userB,userA); //B의 턴을 진행
			if(!this.start) { //현재 필드의 값이 false이면 게임이 끝난것이므로
				rollback(); //값이 바뀌어버린 DB값을 롤백
				WinScoreInsertDB(userA);
				LoseScoreInsertDB(userB);
				DisConnection(); //접속을 종료함.
				break; //반복 종료.
			}
			count++; //총 몇번 오갔는지 저장하위해 증감연산자 사용
			if(count>=20) { //현재 턴 수가 20에 도달하면
				System.out.println("시간 초과, 게임종료");
				if(UserMoneySearch(userA)>UserMoneySearch(userB)) {
					System.out.println(userA+"의 승리!!");
					WinScoreInsertDB(userA);
					LoseScoreInsertDB(userB);
					rollback(); //값이 바뀌어버린 DB값을 롤백
					DisConnection(); //접속을 종료함.
				}
				else if(UserMoneySearch(userA)<UserMoneySearch(userB)) {
					System.out.println(userB+"의 승리!!");
					WinScoreInsertDB(userB);
					LoseScoreInsertDB(userA);
					rollback(); //값이 바뀌어버린 DB값을 롤백
					DisConnection(); //접속을 종료함.
				}
				else {
					rollback(); //값이 바뀌어버린 DB값을 롤백
					DisConnection(); //접속을 종료함.
					System.out.println("무승부");
				}
				break; //반복 종료.
			}
				}
				
			}
		public void WinScoreInsertDB(String userA) {
			String sql="update userlist set win=win+? where name=?";
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, userA);
			pstmt.executeUpdate();
			}
			catch(Exception e) {
			e.printStackTrace();
			}
			
		}
		public void LoseScoreInsertDB(String userA) {
			String sql="update userlist set lose=lose+? where name=?";
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, userA);
			pstmt.executeUpdate();
			}
			catch(Exception e) {
			e.printStackTrace();
			}
			
		}
		public int TurnA(int locationA,String userA,String userB) { //A의 턴을 진행해주는 메소드
			String name = userA;
			System.out.println("");
			System.out.println(name + "의 차례입니다.");
			System.out.println("주사위를 굴리시려면  엔터를 입력하세요.");
			String enter = scan.nextLine();
			int dice = dice(); // 주사위 값을 받는 변수
			locationA += dice; // 현재위치에 주사위값을 누적합
			if (locationA >= 13) { // 최대 이동거리는 12인데 현재위치가 13보다 크거나 같아지는 경우
				UserMoneyBonus(name);
				locationA = locationA % 12; // 현재위치를 12로 나눈 나머지로 바꿈.ex)현위치가 11일 주사위 3이나오면 다음 위치는 14인데 14%12를 하면 현재위치는
											// 2가 됨.
				ACityproPertyCheck(locationA, name,userB); // A유저가 도시에 도착했을시 도시가 공백지인지,자신의 도시인지,타인의 도시인지 판단해주는 메소드.
				return locationA;
			} else {
				ACityproPertyCheck(locationA, name,userB);// A유저가 도시에 도착했을시 도시가 공백지인지,자신의 도시인지,타인의 도시인지 판단해주는 메소드.
			}
			return locationA;
		}
		public int TurnB(int locationB,String userB,String userA) { //B의 턴을 진행해주는 메소드
			String othername = userB;
			System.out.println("");
			System.out.println(othername + "의 차례입니다.");
			System.out.println("주사위를 굴리시려면  엔터를 입력하세요.");
			String enter = scan.nextLine();
			int dice = dice();
			locationB += dice;
			if (locationB >= 13) {
				UserMoneyBonus(othername);
				locationB = locationB % 12;
				BCityproPertyCheck(locationB, othername,userA);// B유저가 도시에 도착했을시 도시가 공백지인지,자신의 도시인지,타인의 도시인지 판단해주는 메소드.
				return locationB;
			} else {
				BCityproPertyCheck(locationB, othername,userA);// B유저가 도시에 도착했을시 도시가 공백지인지,자신의 도시인지,타인의 도시인지 판단해주는 메소드.
			}
			return locationB;

		}
		public void ACityproPertyCheck(int locationA, String userA,String userB) {// A유저가 도시에 도착했을시 도시가 공백지인지,자신의 도시인지,타인의 도시인지 판단해주는 메소드.
			String sql = "SELECT CITY,PRICE,PROPERTY FROM MONOPOLY WHERE CITYNO=?";
			try {
				if (userA.equals(userA)) { //A유저가 도시에 도착하였을때
					userSave = userB; //상대 유저를 B라고 지정
				} else {
					userSave = userA; //반대의 경우는 A라고 지정.
				}
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, locationA);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					saveMoney=rs.getInt("price");
					if(locationA==1) { //1번위치에 도착한 경우는 아무것도 실행하지 않음.(공백지가 아닌 출발지점)
						return;
					}
					if (locationA == 5) { //국세청이므로 세금을 내는 분기점.
						System.out.println(rs.getString("city")+"에 방문했습니다.");
						System.out.println(saveMoney + "를 지불합니다.");
						UserMoneyTax(saveMoney, userA,userB); //A가 5번에 위치했을때 세금을 납부.
					} 
					else if ((rs.getString("property")).equals(userA)) { //A의 도시에 방문하였을때 분기점.
						System.out.println("당신의 소유 도시인  "+rs.getString("city")+"에 방문했습니다.");
						return;
					} 
					else if ((rs.getString("property")).equals(userSave)) {// 상대 도시에 방문하였을때 분기점.
						System.out.print(rs.getString("city")+"에 방문했습니다.  ");
						System.out.println(rs.getString("property") + "의 소유지 입니다. " + saveMoney + "를 지불합니다.");
						// 세금메소드(rs.getInt("price"))
						UserMoneyPayment(saveMoney, locationA, userA,userB);
					} else { //출발지,국세청,자신의 도시,타인의 도시 모두 아닌 공백지일때 분기점.
						System.out.println(rs.getString("city")+"에 방문했습니다.");
						System.out.println("이 " +rs.getString("city")+"는(은) 소유한 사람이없습니다.  가격: " + saveMoney);
						System.out.println("");
						System.out.println("1.구매|2.패스");
						System.out.println("");
						int input1 = scan.nextInt();// 구매/패스
						switch (input1) {
						case 1:
							CityPurchase(saveMoney, userA,userB,locationA);
							String enter=scan.nextLine();
							break;
						case 2:
							enter = scan.nextLine();
							break;

						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void BCityproPertyCheck(int locationB,String userB,String userA) {// B유저가 도시에 도착했을시 도시가 공백지인지,자신의 도시인지,타인의 도시인지 판단해주는 메소드.
			String sql = "SELECT CITY,PRICE,PROPERTY FROM MONOPOLY WHERE CITYNO=?";
			try {
				if (userB.equals(userB)) {// @
					userSave = userA;
				} else {
					userSave = userB;
				}
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, locationB);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					saveMoney=rs.getInt("price");
					if(locationB==1) {
						return;
					}
					if (locationB == 5) {
						System.out.println(rs.getString("city")+"에 방문했습니다.");
						System.out.println(saveMoney + "를 지불합니다.");
						UserMoneyTax(saveMoney,userB,userA);
					} else if ((rs.getString("property")).equals(userB)) {
						System.out.println("당신의 소유 도시인  "+rs.getString("city")+"에 방문했습니다.");
					} 
					else if((rs.getString("property")).equals(userSave)) {
						System.out.print(rs.getString("city")+"에 방문했습니다.  ");
						System.out.println(rs.getString("property") + "의 소유지 입니다. " + saveMoney + "를 지불합니다.");
						// 세금메소드(rs.getInt("price"))
						UserMoneyPayment(saveMoney, locationB,userB,userA);
					} else {
						System.out.println(rs.getString("city")+"에 방문했습니다.");
						System.out.println("이 " +rs.getString("city")+"는(은) 소유한 사람이없습니다.  가격: " + saveMoney);
						System.out.println("");
						System.out.println("1.구매|2.패스");
						System.out.println("");
						int input1 = scan.nextInt();// 구매/패스
						switch (input1) {
						case 1:
							CityPurchase(saveMoney, userB,userA,locationB);
							String enter=scan.nextLine();
							break;
						case 2:
							enter=scan.nextLine();
							break;

						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void UserMoneyPayment(int price, int locationA, String userA, String userB) {// 상대에서 도시의 가격만큼 값을 지불하는 메소드
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			String city=CitySearch(locationA);
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userA);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					saveMoney = rs.getInt("money");
				}
				if (saveMoney <= price) {// 현재 잔액보다 지불금액이 높거나 같다면 파산하고 게임이 종료되는 분기점
					if (userA.equals(userA)) { //만약 위에서 A가 지불을 했다면 B는 돈을 받아야하므로 A와 B를 전환.
						userSave = userB;
					} else {
						userSave = userA; //반대로 B가 지불을 했다면 B와 A를 전환.
					}
					System.out.println(userA+"님이 파산하셨습니다");
					System.out.println(userSave+"가 승리하였습니다.");
					System.out.println("");
					System.out.println("게임종료");
					System.out.println("");
					System.out.println("");
					this.start=false; //게임을 종료시키기위해 현재 필드의 값을 false로 변경
				} 
				else if(saveMoney >= price) {

					sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, userA);
					pstmt.executeUpdate();
					if (userA.equals(userA)) { //만약 위에서 A가 지불을 했다면 B는 돈을 받아야하므로 A와 B를 전환.
						userSave = userB;
					} else {
						userSave = userA; //반대로 B가 지불을 했다면 B와 A를 전환.
					}
					sql = "UPDATE USERLIST SET MONEY=MONEY+? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, userSave);// 상대유저 넣는곳
					pstmt.executeUpdate();
					
					int saveMoney=UserMoneySearch(userA);
					if(saveMoney >= price) {
					System.out.println("해당 도시를 인수하시겠습니까?");
					System.out.println("");
					System.out.println("1.네 2.아니오");
					int command=scan.nextInt();
					if(command==1) {
					sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, userA);
					pstmt.executeUpdate();
					if (userA.equals(userA)) { //만약 위에서 A가 지불을 했다면 B는 돈을 받아야하므로 A와 B를 전환.
						userSave = userB;
					} else {
						userSave = userA; //반대로 B가 지불을 했다면 B와 A를 전환.
					}
					sql = "UPDATE USERLIST SET MONEY=MONEY+? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, userSave);// 상대유저 넣는곳
					pstmt.executeUpdate();
					
					sql = "UPDATE monopoly SET property=? WHERE cityno=?"; //도시의 소유권 변경
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, userA);
					pstmt.setInt(2, locationA);
					pstmt.executeUpdate();
					System.out.println(userA+"가 " +city+" 를(을) 인수하였습니다.");
					String enter=scan.nextLine();
				}
					if(command==2) {
						return;
					}
				}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void UserMoneyBonus(String userA) {// 출발지점을 지나면 추가 월급을 제공해주는 메소드
			String sql = "UPDATE USERLIST SET MONEY=MONEY+? WHERE NAME=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, 200);
				pstmt.setString(2, userA);
				pstmt.executeUpdate();
				System.out.println("출발지점을 만나 보너스를 받습니다.");
				System.out.println("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void UserMoneyTax(int price, String userA, String userB) {// 세금 메소드(가격,지불하는계정)
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userA);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					saveMoney = rs.getInt("money");
				}
				if (saveMoney <= price) {// 현재 잔액보다 지불금액이 높거나 같다면 파산하고 게임이 종료되는 분기점
					if (userA.equals(userA)) {
						userSave = userB;
					} else {
						userSave = userA;
					}
					System.out.println(userA+"님이 파산하셨습니다");
					System.out.println(userSave+"가 승리하였습니다.");
					System.out.println("");
					System.out.println("");
					System.out.println("게임종료");
					System.out.println("");
					System.out.println("");
					this.start=false; //게임을 종료시키기위해 현재 필드의 값을 false로 변경

				} else {

					sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, userA);
					pstmt.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void ACitySearch(String user) {//A가 소유중인 도시를 보기위한 메소드
			String sql = "SELECT * FROM MONOPOLY WHERE PROPERTY=?";
			try {
			try {// 도시보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				System.out.print(user+"의 소유 도시 : ");
				while (rs.next()) {
					System.out.print(rs.getString("city")+" ");
				}
				System.out.println("");
			} catch (SQLException e) {
				System.out.println("DB와 연결되어 있지 않습니다. 연결을 먼저 하세요.");
				Monopolymain.main(null);
			}
			}
			catch(NullPointerException e){
				System.out.println("DB와 연결되어 있지 않습니다. 연결을 먼저 하세요.");
				Monopolymain.main(null);
			}

		}
		public void BCitySearch(String user) {//B가 소유중인 도시를 보기위한 메소드
			String sql = "SELECT * FROM MONOPOLY WHERE PROPERTY=?";
			try {// 도시보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				System.out.print(user+"의 소유도시 : ");
				while (rs.next()) {
					System.out.print(rs.getString("city")+" ");
				}
				System.out.println("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public String CitySearch(int locationA) {
			String sql = "SELECT * FROM MONOPOLY WHERE cityno=?";
			try {// 도시보유
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, locationA);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					return rs.getString("city");
				}
				System.out.println("");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}
		public int UserMoneySearch(String user) { //A,B의 현재 잔액을 조회하여 반환해주는 메소드
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			int returnmoney=0;
			try {// 잔액보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					returnmoney=rs.getInt("money");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnmoney;
		}
		public void UserMoneySearchToast(String user) { //A,B의 현재 잔액을 조회하여 출력을 해주는 메소드
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try {// 잔액보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(user + "님의 잔액: " + rs.getInt("money"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void CityPurchase(int price, String userA,String userB,int locationA) { //도시를 구매하는 메소드
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userA);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					saveMoney = rs.getInt("money"); //현재잔액을 얻어옴
				}
				if (saveMoney <= price) { //도시의 가격보다 현재 잔액이 더 적은 경우 도시를 구매하지 못함.
					System.out.println("잔액이 부족합니다");
				}
				if(saveMoney > price) {
					sql = "select * from monopoly where cityno=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, locationA);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						System.out.println(userA+"가 "+ rs.getString("city")+"를 구매하였습니다.");
					}
					sql = "UPDATE MONOPOLY SET PROPERTY=? WHERE PRICE=?"; //도시를 구매하면 도시의 소유권 컬럼의 값이 변경.
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, userA);
					pstmt.setInt(2, price); //이 메소드를 호출한 곳에서 받아온 도시의 가격
					pstmt.executeUpdate();
					sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?"; //도시를 구매하면 유저의 잔액이 변경
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, userA);
					pstmt.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		public void DisConnection() {
			try {
				System.out.println("");
				System.out.println("");
				System.out.println("접속종료");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				con.close();
			} catch (SQLException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
		}
		public void rollback() {
			try {
				System.out.println("리셋");
				con.rollback();
			} catch (SQLException e) {
				// TODO 자동 생성된 catch 블록
				e.printStackTrace();
			}
			
		}
		public void memberAdd() {
			Scanner scan = new Scanner(System.in);
			String sql = "insert into userlist values((select count(*)from userlist)+1,?,?,?,?)";
			try {
				System.out.println("이름");
				String name=scan.nextLine();
				pstmt = con.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.setInt(2, 0);
					pstmt.setInt(3, 0);
					pstmt.setInt(4, 1500);
					pstmt.executeUpdate();
			}
			catch(Exception e) {
				System.out.println("DB접속 실패");
				e.printStackTrace();
			}
			
		}
		public String firstMemberSearch() {
			String sql = "SELECT * FROM USERLIST where userno=?";
			String name=null;
			try {
			pstmt = con.prepareStatement(sql);
			System.out.println("1P의 회원번호를 입력하세요.");
			pstmt.setInt(1, scan.nextInt());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getString("name");
			}
		}
			catch(Exception e) {
				e.printStackTrace();
			}
			return name;
	}
		public String secondMemberSearch() {
			String sql = "SELECT * FROM USERLIST where userno=?";
			String name=null;
			try {
			pstmt = con.prepareStatement(sql);
			System.out.println("2P의 회원번호를 입력하세요.");
			pstmt.setInt(1, scan.nextInt());
			String enter=scan.nextLine();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getString("name");
			}
		}
			catch(Exception e) {
				e.printStackTrace();
			}
			return name;
	}
		public void ScoreSearch() {
			String sql = "SELECT * FROM USERLIST where userno=?";
			try {
			pstmt = con.prepareStatement(sql);
			System.out.println("조회할 회원의 번호를 입력하세요.");
			pstmt.setInt(1, scan.nextInt());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.print(rs.getString("name")+"의 전적 : "+rs.getInt("win")+"승"+rs.getInt("lose")+"패"+"\n"+"\n");
			}
		}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	}