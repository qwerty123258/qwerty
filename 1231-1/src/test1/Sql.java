package test1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Sql {
	Connection con = null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	public void Connection() {
		con=DBcon.DBConnection();
	}
	public boolean CheckAccount(String accountnum) { //계좌존재 여부 판단하는 메서드
		Scanner scan = new Scanner(System.in);
		String sql = "select * from bank where accountnum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,accountnum);
			rs=pstmt.executeQuery(); 
			if(rs.next()) {
				return true;
					}
	}
		 catch(SQLException e) {
			System.out.println("DB접속 실패");
			e.printStackTrace();
		}
		System.out.println("존재하는 계좌가 없습니다.");
		return false;
	}
	public boolean CreateCheckAccount(String accountnum) { //계좌 생성시 중복여부 판단하는 메서드
		String sql = "select * from bank where accountnum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,accountnum);
			rs=pstmt.executeQuery(); 
			if(rs.next()) {
				System.out.println("중복된 계좌번호입니다.");
				return false;
					}
	}
		 catch(SQLException e) {
			System.out.println("DB접속 실패");
			e.printStackTrace();
		}
		return true;
	}
	public int clientnum() { //고객번호 생성 메서드
		Scanner scan = new Scanner(System.in);
		String sql = "select count(*) from bank";
		int clientnum=0;
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery(); 
			if(rs.next()) {
				return rs.getInt("count(*)");
			}
		}
		 catch(SQLException e) {
			System.out.println("DB접속 실패");
			e.printStackTrace();
		 }
		return clientnum;
	}	
	public void CreateAccount() { //계좌개설하는 메서드(고객등록)
		Scanner scan = new Scanner(System.in);
		String sql = "insert into bank values(?,?,?,?)";
		try {
			int clientnum=clientnum();
			System.out.println("계좌번호");
			String accountnum= scan.nextLine();
			if(CreateCheckAccount(accountnum)) {
			System.out.println("이름");
			String name=scan.nextLine();
			pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, clientnum+1);
				pstmt.setString(3,accountnum);
				pstmt.setString(2, name);
				pstmt.setInt(4, 0);
				pstmt.executeUpdate();
			}
		}
		catch(Exception e) {
			System.out.println("DB접속 실패");
			e.printStackTrace();
		}
	}
	public int money(String accountnum) { //현재 잔액을 반환해주는 메서드
		Scanner scan = new Scanner(System.in);
		String sql = "select * from bank where accountnum=?";
		int money=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,accountnum);
			rs=pstmt.executeQuery(); 
			if(rs.next()) {
				rs.getInt("money");
				return rs.getInt("money");
			}
		}
		 catch(SQLException e) {
			System.out.println("DB접속 실패");
			e.printStackTrace();
		 }
		return money;
	}
	public void Deposit() { //money 메서드에서 값을 받아온뒤 누적합으로 입금해주는 메서드
		Scanner scan = new Scanner(System.in);
		String sql = "update bank set money=? where accountnum=?";
		System.out.println("계좌번호");
		String accountnum= scan.nextLine();
		if(CheckAccount(accountnum)) {
			try {
				int savemoney=money(accountnum);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(2,accountnum);
				System.out.println("금액");
				int money=scan.nextInt();
				int sum=savemoney+money;
				pstmt.setInt(1,sum);
				pstmt.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			DepositToast(accountnum);
		}
	}
	public void DepositToast(String accountnum) { //입금시 안내문구 출력용 메서드
		Scanner scan = new Scanner(System.in);
		String sql = "select * from bank where accountnum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,accountnum);
				rs=pstmt.executeQuery(); 
				while(rs.next()) {
						System.out.println("입금후 잔액 :"+rs.getInt("money"));
						}
		}
			 catch(SQLException e) {
				System.out.println("DB접속 실패");
				e.printStackTrace();
			 }
	}

	public void Withdraw() { //money 메서드에서 값을 받아온뒤 누적빼기로 출금해주는 메서드
		Scanner scan = new Scanner(System.in);
		String sql = "update bank set money=? where accountnum=?";
		System.out.println("계좌번호");
		String accountnum= scan.nextLine();
		if(CheckAccount(accountnum)) {
			try {
				int savemoney=money(accountnum);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(2,accountnum);
				System.out.println("금액");
				int money=scan.nextInt();
				if(savemoney>money) {
				int sum=savemoney-money;
				pstmt.setInt(1,sum);
				pstmt.executeUpdate();
			}
				else System.out.println("잔액이 부족합니다.");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		WithdrawToast(accountnum);
	}
	public void WithdrawToast(String accountnum) { //출금시 안내문구 출력용 메서드
		Scanner scan = new Scanner(System.in);
		String sql = "select * from bank where accountnum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,accountnum);
				rs=pstmt.executeQuery(); 
				while(rs.next()) {
						System.out.println("출금후 잔액 :"+rs.getInt("money"));
						}
		}
			 catch(SQLException e) {
				System.out.println("DB접속 실패");
				e.printStackTrace();
			 }
	}
	public void Transfer() { //송금 기능을 하는 메서드
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호");
		String accountnum= scan.nextLine();
		if(CheckAccount(accountnum)) {
				System.out.println("이체할 계좌번호");
				String otheraccountnum= scan.nextLine();
				if(CheckAccount(otheraccountnum)) {
					if(accountnum.equals(otheraccountnum)) {
						System.out.println("자신의 계좌로는 이체가 불가능합니다.");
					}
					else {
						System.out.println("이체할 금액");
						int money=scan.nextInt();
						if(CheckAccount(accountnum) && CheckAccount(otheraccountnum)) {
							TransferWithdraw(accountnum,otheraccountnum,money);
					}
			}
		}
		}
	}
	public void TransferWithdraw(String accountnum,String otheraccountnum,int money) { //송금할때 본인 계좌에서 출금을 해주는 메서드
		Scanner scan = new Scanner(System.in);
		String sql = "update bank set money=? where accountnum=?";
		if(CheckAccount(accountnum)) {
			try {
				int savemoney=money(accountnum);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(2,accountnum);
				if(savemoney>money) {
				int sum=savemoney-money;
				pstmt.setInt(1,sum);
				pstmt.executeUpdate();
				TransferDeposit(otheraccountnum, money);
				TransferToast(accountnum);
				}
				else System.out.println("잔액이 부족합니다.");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void TransferDeposit(String otheraccountnum,int money) { //송금할때 타인 계좌는 입금이 되는 메서드
		String sql = "update bank set money=? where accountnum=?";
		if(CheckAccount(otheraccountnum)) {
			try {
				int savemoney=money(otheraccountnum);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(2,otheraccountnum);
				int sum=savemoney+money;
				pstmt.setInt(1,sum);
				pstmt.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void TransferToast(String accountnum) { //송금시 안내문구 출력용 메서드
		Scanner scan = new Scanner(System.in);
		String sql = "select * from bank where accountnum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,accountnum);
				rs=pstmt.executeQuery(); 
				while(rs.next()) {
						System.out.println("이체후 잔액 :"+rs.getInt("money"));
				}
		}
			 catch(SQLException e) {
				System.out.println("DB접속 실패");
				e.printStackTrace();
			 }
	}
	public void InquiryAccount() { //잔액 확인용 메서드
			Scanner scan = new Scanner(System.in);
			String sql = "select name,money from bank where accountnum=?";
			try {
				pstmt = con.prepareStatement(sql);
				System.out.println("계좌번호");
				String accountnum= scan.next();
				if(CheckAccount(accountnum)) {
					pstmt.setString(1,accountnum);
					rs=pstmt.executeQuery(); 
					while(rs.next()) {
						System.out.println(rs.getString("name")+"의 잔액 :"+ rs.getInt("money"));
					}
				}
			}
				 catch(SQLException e) {
					System.out.println("DB접속 실패");
					e.printStackTrace();
				 }
	}
	public void BankList() {
		String sql = "select * from bank";
		try {
			pstmt = con.prepareStatement(sql);
				rs=pstmt.executeQuery(); 
				while(rs.next()) {
					System.out.print("고객번호 : "+rs.getInt("clientnum")+"\t");
					System.out.print("이름 : "+rs.getString("name")+"\t");
					System.out.print("계좌번호 : "+rs.getString("accountnum")+"\t");
					System.out.println("잔액 : "+rs.getInt("money")+"\n"+"\t");
				}
			}
			 catch(SQLException e) {
				System.out.println("DB접속 실패");
				e.printStackTrace();
			 }
		
	}
} 