package Monopoly;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Monopolymain {
	public static void main(String[] args) {
		DBsql sql = new DBsql();
		Scanner scan= new Scanner(System.in);
		try {
		for(;;) {
			System.out.println("1.게임 준비  2.회원 등록 3.게임 시작  4.전적조회 5.종료");
			int command=scan.nextInt();
			if(command==1) {
				sql.Connection();// 게임과 DB를 연결해주는 메서드	
			}
			if(command==2) {
				sql.memberAdd();
			}
			if(command==3) {
				sql.AutoCommitOff();//자동 커밋을 off해주는 메서드,맵에 관한 데이터는 늘 일정해야하므로 임시 변경만 되어야해서 커밋은 해제.
				sql.Start(); //게임에서 턴을 반복하는 메서드
			}
			if(command==4) {
				sql.ScoreSearch();
			}
			if(command==5) {
				break;
			}
		}
	}
	catch(InputMismatchException e) {
		System.out.println("InputMismatchException!!");
		main(args);
	}
	}
}