	package test1;
	import java.sql.*;
	import java.util.*;
	public class DBsql {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userSave = "";
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
				e.printStackTrace();
			}
		}
		public void TurnRepeat() { // 2번 커맨드 누르면 실행이며 1P와 2P의 턴이 반복됨.(전석종)
			String userA=firstMemberSearch(); //USER A 이름 변수
			String userB=secondMemberSearch(); //USER B 이름 변수
			int locationA = 1; // A의 위치값 저장용 변수
			int locationB = 1; // B의 위치값 저장용 변수
			int count=1; //몇턴을 진행하였는지 저장하기 위한 변수
			this.start=true; //이 클래스의 boolean 필드 값을 true로 설정한다. >>게임이 한 판 끝난 뒤에는 해당 필드의 값이 false인데 이를 다시 true로 바꿔주며 다시 게임이 시작되게 한다.
			while (this.start) { //현재 필드의 값이 true이면
				map();
				locationA=TurnA(locationA,userA,userB); //A의턴을 진행.
				if(!this.start) { //현재 필드의 값이 false이면 게임이 끝난것이므로
					ALose(userA,userB);
					break; //반복 종료.
				}
				gameInfo(count,userA,userB);
				map();
				locationB=TurnB(locationB,userB,userA); //B의 턴을 진행
				if(!this.start) { //현재 필드의 값이 false이면 게임이 끝난것이므로
					BLose(userA,userB);
					break; //반복 종료.
				}
				gameInfo(count,userA,userB);
				count++; //총 몇번 오갔는지 저장하위해 증감연산자 사용
				if(count>=20) { //현재 턴 수가 20에 도달하면
					System.out.println("시간 초과, 게임종료");
					if(UserMoneySearch(userA)>UserMoneySearch(userB)) {
						System.out.println(userA+"의 승리!!");
						BLose(userA,userB);
					}
					else if(UserMoneySearch(userA)<UserMoneySearch(userB)) {
						System.out.println(userB+"의 승리!!");
						ALose(userA,userB);
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
			System.out.println("+   찬      +   대      +   광        +   독        +");
			System.out.println("+   스      +   구      +   주        +   도        +");
			System.out.println("+-------+-------+--------+--------+");
		}
		public void gameInfo(int count,String userA, String userB) { //현재 게임의 진행상황을 보여주는 메소드
			System.out.println("");
			System.out.println(count+"턴");
			CitySearchToast(userA);
			UserMoneySearchToast(userA);
			CitySearchToast(userB);
			UserMoneySearchToast(userB);			
		}
		public void BLose(String userA, String userB) { //A가 이기고 B가 진경우에 실행하는 메소드
			rollback(); //값이 바뀌어버린 DB값을 롤백
			WinScoreInsertDB(userA);
			LoseScoreInsertDB(userB);
			DisConnection(); //접속을 종료함.
		}
		public void ALose(String userA, String userB) {//B가 이기고 A가 진경우에 실행하는 메소드
			rollback(); //값이 바뀌어버린 DB값을 롤백
			WinScoreInsertDB(userB); //이긴 유저에게 스코어 추가
			LoseScoreInsertDB(userA); // 진 유저에게 스코어 추가
			DisConnection(); //접속을 종료함.
		}
		public int dice() { // 주사위를 굴려서 값을 반환하는 메소드
			int dice = (int) (Math.random() * 6) + 1;
			return dice;
		}
		public int TurnA(int locationA,String userA,String userB) { //1P의 턴을 진행해주는 메소드(전석종)
			String name = userA;
			System.out.println("");
			System.out.println(name + "의 차례입니다.");
			System.out.println("주사위를 굴리시려면  엔터를 입력하세요.");
			String enter = scan.nextLine(); //공백을 입력해도 내용이 진행되게끔 nextLine 메소드 사용
			int dice = dice(); // 주사위 값을 받는 변수
			locationA += dice; // 현재위치에 주사위값을 누적합
			if (locationA >= 13) { // 최대 이동거리는 12인데 현재위치가 13보다 크거나 같아지는 경우
				UserMoneyBonus(name);
				locationA = locationA % 12; // 현재위치를 12로 나눈 나머지로 바꿈.ex)현위치가 11일 주사위 3이나오면 다음 위치는 14인데 14%12를 하면 현재위치는 2
				CityproPertyCheck(locationA, name,userB); // A유저가 도시에 도착했을시 도시가 공백지인지,자신의 도시인지,타인의 도시인지 판단해주는 메소드.
				return locationA;
			} 
			else {
				CityproPertyCheck(locationA, name,userB);// A유저가 도시에 도착했을시 도시가 공백지인지,자신의 도시인지,타인의 도시인지 판단해주는 메소드.
			}
			return locationA;
		}
		public int TurnB(int locationB,String userB,String userA) { //2P의 턴을 진행해주는 메소드(전석종)
			String othername = userB;
			System.out.println("");
			System.out.println(othername + "의 차례입니다.");
			System.out.println("주사위를 굴리시려면  엔터를 입력하세요.");
			String enter = scan.nextLine(); //공백을 입력해도 내용이 진행되게끔 nextLine 메소드 사용
			int dice = dice();
			locationB += dice;
			if (locationB >= 13) {
				UserMoneyBonus(othername);
				locationB = locationB % 12;
				CityproPertyCheck(locationB, othername,userA);// B유저가 도시에 도착했을시 도시가 공백지인지,자신의 도시인지,타인의 도시인지 판단해주는 메소드.
				return locationB;
			}
			else {
				CityproPertyCheck(locationB, othername,userA);// B유저가 도시에 도착했을시 도시가 공백지인지,자신의 도시인지,타인의 도시인지 판단해주는 메소드.
			}
			return locationB;
		}
		public void CityproPertyCheck(int locationA, String userA,String userB) {
			//설명:리턴된 값에 따라 해당 유저가 유저가 도시에 도착했을시 어느 도시인지 출력후     (김현) 
			//도착한 곳이 공백도시인지,자신의 도시인지,타인의 도시인지,국세청인지,출발지,찬스인지에 따라 실행되야되는 기능이 다르므로 
			//현재위치를 판단 후 도착한 곳에 따라 각기 다른 기능을 실행해주는 메소드를 생성.
			//locationA=움직인값 USER A=유저1 USER B=유저2
			//CITY=도시이름   PRICE=도시가격  PROPERTY=소유권  CITYNO=도시번호
			//리턴된 현재위치를 매개변수로 받아와 도시번호에 대입하여 도착한 도시와 도시의 가격,소유권 여부를 조회함.
			String sql = "SELECT CITY,PRICE,PROPERTY FROM MONOPOLY WHERE CITYNO=?";  
			try {  
				if (userA.equals(userA)) { //게임을이용하는 유저는 2명이므로 어느유저가 이용하는지 판별하기위해 IF문을씀
					userSave = userB; //상대 유저를 B라고 지정
				} 
				else {
					userSave = userA; //반대의 경우는 A라고 지정.
				}
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, locationA);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int price=rs.getInt("price");
					if(locationA==1) { //1번위치는 출발지점이라 변해야할값이 없으므로 리턴  
						return;   
					}
					else if (locationA == 5) { //국세청일땐 세금(지불)만 내게끔 IF문사용 
						System.out.println(rs.getString("city")+"에 방문했습니다.");
						System.out.println(price + "를 지불합니다.");
						UserMoneyTax(price, userA,userB); //유저가 5번에 위치했을때  메세지 출력후 세금메소드 실행
					}
					else if (locationA == 10) { //찬스 IF문
						System.out.println(rs.getString("city")+"에 방문했습니다.");
						Chance(userA);//유저가 10번에 위치했을때  메세지출력후 찬스 메소드실행
					}
					else if ((rs.getString("property")).equals(userA)) { //내도시 IF문
						System.out.println("당신의 소유 도시인  "+rs.getString("city")+"에 방문했습니다.");
						return;//자신의 소유지면 더 이상의 실행내용 없으므로 해당 메소드 종료
					} 
					else if ((rs.getString("property")).equals(userSave)) {// 상대도시 IF문
						System.out.print(rs.getString("city")+"에 방문했습니다.  ");
						System.out.println(rs.getString("property") + "의 소유지 입니다. " + price + "를 지불합니다.");
						UserMoneyPayment(price, locationA, userA,userB);//상대의 소유지일땐 메세지 출력후 지불 메소드 진행
					} 
					else { //출발지,국세청,자신의 도시,타인의 도시 모두 아닌 공백지일때 IF문
						System.out.println(rs.getString("city")+"에 방문했습니다.");
						System.out.println("이 " +rs.getString("city")+"는(은) 소유한 사람이없습니다.  가격: " + price);
						System.out.println("");
						System.out.println("1.구매|2.패스");
						System.out.println("");
						int input1 = scan.nextInt();
						switch (input1) {// 메세지출력후 구매/패스 고르는 SWITCH문
						case 1:
							CityPurchase(price, userA,userB,locationA);//무소유지 도시 구매 메소드
							String enter=scan.nextLine(); //도시 구매를 할때는 nextLINE() 메소드를 사용하는데 이는 Enter전까지 입력받으므로 남아있는 Enter를 소진시키는 용도
							break;
						case 2:
							enter = scan.nextLine();//도시 구매를 할때는 next() 메소드를 사용하는데 이는 Enter전까지 입력받으므로 남아있는 Enter를 소진시키는 용도
							break;						
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void Chance(String userA) { //1P,2P가 찬스 지역에 위치하는 실행되는 메소드,랜덤한 확률에 의해 실행.(전석종)
			int chance=(int)(Math.random()*3)+1;
			System.out.println("찬스 발동");
			if(chance==1) {
				System.out.println("당첨!! 10%의 금액이 가산됩니다.");
				String sql="update userlist set money=money+money/10 where name=?";
				try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userA);
				pstmt.executeUpdate();
				pstmt.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(chance==2) {
				System.out.println("가위바위보 미니게임 시작!");
				System.out.println("컴퓨터와 가위바위보를 하여 이기면 상금,지면 벌금");
				boolean win=minigame();
				if(win) {
					String sql="update userlist set money=money+1000 where name=?";
					try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, userA);
					pstmt.executeUpdate();
					pstmt.close();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				if(!win) {
					String sql="update userlist set money=money-500 where name=?";
					try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, userA);
					pstmt.executeUpdate();
					pstmt.close();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			if(chance==3) {
				System.out.println("꽝!! 10%의 금액이 차감됩니다.");
				String sql="update userlist set money=money-money/10 where name=?";
				try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userA);
				pstmt.executeUpdate();
				pstmt.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}	
			}
		}
		public boolean minigame() { //미니게임 가위바위보를 진행하는 메소드 결과에 따라 boolean 값을 반환.(전석종)
			Scanner scan = new Scanner(System.in);
			System.out.println("컴퓨터와 가위바위보를 진행하세요. 키워드는 가위,바위,보만 가능합니다.");
			String userinput = scan.next(); // 유저가 입력하는 변수.
			int computer=0; // 컴퓨터의 Math.random() 결과 대입 하는 변수.
			String computeroutput = null; // 컴퓨터의 Math.random() 결과에 따라서 유저 변수와 비교하기 위한 변수.
			computer= (int) ((Math.random() * 3) +1); // 곱하기 3을 하는이유는 가위바위보는 경우의 수가 3가지뿐이라 숫자도 3개만 있으면 됨.
			if(computer==1) {
				computeroutput = "가위";
			}
			else if(computer==2){
				computeroutput = "바위";
			}
			else if(computer==3) {
				computeroutput = "보";
			}
			if(!userinput.equals("가위")) {
				if(!userinput.equals("바위")) {
					if(!userinput.equals("보")) {
						if(!userinput.equals("종료")) {
							System.out.println("잘못 입력하셨습니다.");
							return false;
						}			
					}		
				}		
			}
			switch(computeroutput) {
			case "가위" :
				if(userinput.equals("가위")) {System.out.println("비겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput); return false;}
				else if(userinput.equals("바위")) {System.out.println("이겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput); return true;}
				else if(userinput.equals("보")) {System.out.println("졌습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput); 	return false;}
				break;
			case "바위" :
				if(userinput.equals("가위")) {System.out.println("졌습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput); return false;}
				else if(userinput.equals("바위")) {System.out.println("비겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput); return false;}
				else if(userinput.equals("보")) {System.out.println("이겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput); return true;}
				break;
			case "보" : 
				if(userinput.equals("가위")) {System.out.println("이겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput); return true;}
				else if(userinput.equals("바위")) {System.out.println("졌습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput); return false;}
				else if(userinput.equals("보")) {System.out.println("비겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput); return false;}
				break;
			}		
			return false;
		}
		public void CityPurchase(int price, String userA,String userB,int locationA) { //도시를 구매하는 메소드
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userA);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int saveMoney = rs.getInt("money"); //현재잔액을 얻어옴
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
					pstmt.close();
					sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?"; //도시를 구매하면 유저의 잔액이 변경
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, userA);
					pstmt.executeUpdate();
					pstmt.close();
				}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void UserMoneyPayment(int price, int locationA, String userA, String userB) {
			// 상대에서 도시의 가격만큼 값을 지불하는 메소드(김현)
			//순서=값을 지불해야하는 유저의 잔액 조회>>1.지불할 금액보다 잔액이 적으면 파산후 게임종료
			//1.돈이 있을 경우엔 지불 후 인수 유무 판단>>2.가격보다 넘게 돈 있으면 인수여부 판단
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?"; 			                
			try {                                                            
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userA);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int saveMoney = rs.getInt("money");
				if (saveMoney <= price) {// 현재 잔액보다 지불금액이 높거나 같다면 파산하고 게임이 종료되는 분기점
					if (userA.equals(userA)) { //만약 위에서 A가 지불을 했다면 B는 돈을 받아야하므로 A와 B를 전환.
						userSave = userB;
					} 
					else {
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
					pstmt.close();
					if (userA.equals(userA)) { //만약 위에서 A가 지불을 했다면 B는 돈을 받아야하므로 A와 B를 전환.
						userSave = userB;
					} 
					else {
						userSave = userA; //반대로 B가 지불을 했다면 B와 A를 전환.
					}
					sql = "UPDATE USERLIST SET MONEY=MONEY+? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, userSave);// 상대유저 넣는곳
					pstmt.executeUpdate();
					pstmt.close();
					TakeOverCity(price,userA,userB,locationA);
				}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void TakeOverCity(int price,String userA,String userB,int locationA) {//현재 위치의 도시가 다른 사람의 소유지일경우 인수 유무를 결정하는 메소드(전석종)
			int saveMoney=UserMoneySearch(userA);
			String city=CitySearch(locationA);
			if(saveMoney >= price) {
				System.out.println("해당 도시를 인수하시겠습니까?");
				System.out.println("");
				System.out.println("1.네 2.아니오");
				int command=scan.nextInt();
				if(command==1) {
					String sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?";
					try {
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, price);
						pstmt.setString(2, userA);
						pstmt.executeUpdate();
						pstmt.close();
						if (userA.equals(userA)) { //만약 위에서 A가 지불을 했다면 B는 돈을 받아야하므로 A와 B를 전환.
							userSave = userB;
						} 
						else {
							userSave = userA; //반대로 B가 지불을 했다면 B와 A를 전환.
						}
						sql = "UPDATE USERLIST SET MONEY=MONEY+? WHERE NAME=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, price);
						pstmt.setString(2, userSave);// 상대유저 넣는곳
						pstmt.executeUpdate();
						pstmt.close();
						sql = "UPDATE monopoly SET property=? WHERE cityno=?"; //도시의 소유권 변경
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, userA);
						pstmt.setInt(2, locationA);
						pstmt.executeUpdate();
						System.out.println(userA+"가 " +city+" 를(을) 인수하였습니다.");
						String enter=scan.nextLine(); //도시 인수를 할때는 next() 메소드를 사용하는데 이는 Enter전까지 입력받으므로 남아있는 Enter를 소진시키는 용도
						pstmt.close();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					if(command==2) {
						return;
					}
				}
			}
		}
		public void UserMoneyBonus(String userA) {// 1P,2P가 출발지점을 지나면 추가 월급을 제공해주는 메소드(전석종)
			String sql = "UPDATE USERLIST SET MONEY=MONEY+? WHERE NAME=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, 200);
				pstmt.setString(2, userA);
				pstmt.executeUpdate();
				pstmt.close();
				System.out.println("출발지점을 만나 보너스를 받습니다.");
				System.out.println("");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void UserMoneyTax(int price, String userA, String userB) {// 세금 메소드(가격,지불하는계정)(김현)
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userA);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int saveMoney = rs.getInt("money");
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
				} 
				else {
					sql = "UPDATE USERLIST SET MONEY=MONEY-? WHERE NAME=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, price);
					pstmt.setString(2, userA);
					pstmt.executeUpdate();
					pstmt.close();
				}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void CitySearchToast(String user) {//1P,2P가 현재 소유중인 도시를 출력해주는 메소드(전석종)
			String sql = "SELECT * FROM MONOPOLY WHERE PROPERTY=?";
			try {// 도시보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				System.out.print(user+"의 소유 도시 : ");
				while (rs.next()) {
					System.out.print(rs.getString("city")+" ");
				}
				System.out.println("");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DB와 연결되어 있지 않습니다. 연결을 먼저 하세요.");
				Monopolymain.main(null);
			}
		}
		public String CitySearch(int locationA) { //현재 위치에 맞는 도시번호를 조회하여 해당 도시의 이름을 반환해주는 메소드(전석종)
			String sql = "SELECT * FROM MONOPOLY WHERE cityno=?";
			try {// 도시보유
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, locationA);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					return rs.getString("city");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		public int UserMoneySearch(String user) { //1P,2P의 현재 잔액을 조회하여 반환해주는 메소드(김현)
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
				e.printStackTrace();
			}
			return returnmoney;
		}
		public void UserMoneySearchToast(String user) { //A,B의 현재 잔액을 조회하여 출력을 해주는 메소드(전석종)
			String sql = "SELECT MONEY FROM USERLIST WHERE NAME=?";
			try {// 잔액보유
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println(user + "님의 잔액: " + rs.getInt("money"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void DisConnection() { //게임이 끝난뒤에 연결을 해제해주는 메소드(전석종)
			try {
				System.out.println("");
				System.out.println("");
				System.out.println("접속종료");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void rollback() { //게임이 끝난뒤에 임시저장된 내용들을 롤백(리셋)해주는 메소드(전석종)
			try {
				System.out.println("리셋");
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void memberAdd() { //회원 리스트 DB에 유저를 추가시켜주는 메소드(회원가입)(전석종)
			Scanner scan = new Scanner(System.in);
			String sql = "insert into userlist values((select count(*)from userlist)+1,?,?,?,?)";
			try {
				System.out.println("이름");
				String name=scan.next();
				pstmt = con.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.setInt(2, 0);
					pstmt.setInt(3, 0);
					pstmt.setInt(4, 1500);
					pstmt.executeUpdate();
					pstmt.close();
			}
			catch(Exception e) {
				System.out.println("DB접속 실패");
				e.printStackTrace();
			}
		}
		public String firstMemberSearch() {//1P를 조회하고 플레이에 참여시키는 메소드(전석종)
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
			if(!rs.next()) {
				System.out.println("해당하는 회원이 없습니다.");
				TurnRepeat();
			}
		}
			catch(Exception e) {
				e.printStackTrace();
			}
			return name;
	}
		public String secondMemberSearch() { //2P를 조회하고 플레이에 참여시키는 메소드(전석종)
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
			if(!rs.next()) {
				System.out.println("해당하는 회원이 없습니다.");
				TurnRepeat();
			}
		}
			catch(Exception e) {
				e.printStackTrace();
			}
			return name;
	}
		public void WinScoreInsertDB(String userA) { //승리 스코어를 DB에 반영하는 메소드(전석종)
			String sql="update userlist set win=win+? where name=?";
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, userA);
			pstmt.executeUpdate();
			pstmt.close();
			}
			catch(Exception e) {
			e.printStackTrace();
			}
		}
		public void LoseScoreInsertDB(String userA) { //패배 스코어를 DB에 반영하는 메소드(전석종)
			String sql="update userlist set lose=lose+? where name=?";
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, userA);
			pstmt.executeUpdate();
			pstmt.close();
			}
			catch(Exception e) {
			e.printStackTrace();
			}
		}
		public void ScoreSearch() { //전적 조회용 메소드,이름을 입력하면 출력이 되는데 이름을 다르게 입력하면 출력이 안됨.(전석종)
			String sql = "SELECT * FROM USERLIST where name=?";
			try {
			pstmt = con.prepareStatement(sql);
			System.out.println("조회할 회원의 이름을 입력하세요.");
			String name= scan.next();
			pstmt.setString(1,name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(name.equals(rs.getString("name"))) {
					System.out.print(rs.getString("name")+"의 전적 : "+rs.getInt("win")+"승"+rs.getInt("lose")+"패"+"\n"+"\n");	
				}
			}
		}
			catch(Exception e) {
				System.out.println("DB와 연결되어있지 않습니다.");
			}
		}
	}