package test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bankmain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Sql sql = new Sql();
		for(;;) {
			System.out.println("-------------------------------------------------------------------");
			System.out.println("1.DB에 접속 | 2.계좌생성 | 3.입금하기 | 4.출금하기 | 5.송금하기 | 6.잔액확인 | 7.종료");
			System.out.println("--------------------------------------------------------------------");
		int command=scan.nextInt();
		if(command==1) {
			sql.Connection();
		}
		if(command==2) {
			sql.CreateAccount();
		}
		if(command==3) {
			sql.Deposit();
		}
		if(command==4) {
			sql.Withdraw();
		}
		if(command==5) {
			sql.Transfer();
		}
		if(command==6) {
			sql.InquiryAccount();
		}
		if(command==7) {
			System.out.println("종료되었습니다.");
			break;
		}
		}

	}

}
