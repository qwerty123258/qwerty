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

	public void CreateAccount(int clientnum) {
		Scanner scan = new Scanner(System.in);
		String sql = "insert into bank values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println("계좌번호");
			String accountnum= scan.next();
			if(CreateAccount(accountnum)) {
				pstmt.setInt(1, clientnum);
				System.out.println("이름");
				pstmt.setString(2, scan.next());
				pstmt.setString(3,accountnum);
				pstmt.setInt(4, 0);
				pstmt.executeUpdate();
			}
}
		catch(Exception e) {
			System.out.println("DB접속 실패");
		}
	}

	public void Deposit() {
		Scanner scan = new Scanner(System.in);
		String sql = "update bank set money=? where accountnum=?";
		System.out.println("계좌번호");
		String accountnum= scan.nextLine();
		if(Account(accountnum)) {
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
			InquiryAccount2(accountnum);

	}
	}

	public void Withdraw() {
		Scanner scan = new Scanner(System.in);
		String sql = "update bank set money=? where accountnum=?";
		System.out.println("계좌번호");
		String accountnum= scan.nextLine();
		if(Account(accountnum)) {
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
		
		InquiryAccount3(accountnum);
		
	}
	
	public void Withdraw2(String accountnum,String otheraccountnum,int money) {
		Scanner scan = new Scanner(System.in);
		String sql = "update bank set money=? where accountnum=?";
		if(Account(accountnum)) {
			try {
				int savemoney=money(accountnum);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(2,accountnum);
				if(savemoney>money) {
				int sum=savemoney-money;
				pstmt.setInt(1,sum);
				pstmt.executeUpdate();
				Deposit2(otheraccountnum, money);
				InquiryAccount4(accountnum);
			}
				else System.out.println("잔액이 부족합니다.");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void Deposit2(String otheraccountnum,int money) {
		String sql = "update bank set money=? where accountnum=?";
		if(Account(otheraccountnum)) {
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

	public void Transfer() {
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호");
		String accountnum= scan.nextLine();
		System.out.println("이체할 계좌번호");
		String otheraccountnum= scan.nextLine();
		if(accountnum.equals(otheraccountnum)) {
			System.out.println("자신의 계좌로는 이체가 불가능합니다.");
		}
		else {
			System.out.println("이체할 금액");
			int money=scan.nextInt();
			if(Account(accountnum) && Account(otheraccountnum)) {
				Withdraw2(accountnum,otheraccountnum,money);
		}
		}
	}

	public void InquiryAccount() {
			Scanner scan = new Scanner(System.in);
			String sql = "select name,money from bank where accountnum=?";
			try {
				pstmt = con.prepareStatement(sql);
				System.out.println("계좌번호");
				String accountnum= scan.next();
				if(Account(accountnum)) {
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
	public String InquiryAccount(String accountnum) {
		Scanner scan = new Scanner(System.in);
		String sql = "select name,money from bank where accountnum=?";
		try {
			pstmt = con.prepareStatement(sql);
			if(Account(accountnum)) {
				pstmt.setString(1,accountnum);
				rs=pstmt.executeQuery(); 
				while(rs.next()) {
					return rs.getString("name")+"의 잔액 :"+ rs.getInt("money");
						}
			}
		}
			 catch(SQLException e) {
				System.out.println("DB접속 실패");
				e.printStackTrace();
			}
		return null;
			}
	public void InquiryAccount2(String accountnum) {
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
	
	public void InquiryAccount3(String accountnum) {
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
	
	public void InquiryAccount4(String accountnum) {
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
	public boolean Account(String accountnum) {
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
	public int money(String accountnum) {
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
	public boolean CreateAccount(String accountnum) {
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
	
}
