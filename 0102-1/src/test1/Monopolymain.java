package test1;

import java.util.Scanner;

public class Monopolymain {
	public static void main(String[] args) {
		DBsql sql = new DBsql();
		Scanner scan= new Scanner(System.in);
		for(;;) {
			System.out.println("1.게임 준비  2.회원 등록 3.게임 시작 4.종료");
			int command=scan.nextInt();
			if(command==1) {
				sql.Connection();// 게임과 DB를 연결해주는 메서드	
			}
			if(command==2) {
				sql.memberAdd();
			}
			if(command==3) {
				sql.AutoCommitOff();//자동 커밋을 off해주는 메서드
				sql.TurnRepeat(); //게임에서 턴을 반복하는 메서드
			}
			if(command==4) {
				break;
			}
		}
		}
	}