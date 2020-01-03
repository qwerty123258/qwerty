package test1;

import java.io.IOException;
import java.util.Scanner;

public class Monopolymain {
	public static void main(String[] args) throws IOException {
		DBsql sql = new DBsql();
		boolean start;
		Scanner scan= new Scanner(System.in);
		for(;;) {
			System.out.println("1.게임 시작  2.종료");
			int command=scan.nextInt();
			if(command==1) {
				sql.Connection();// 게임과 DB를 연결해주는 메서드	
				sql.AutoCommitOff();//자동 커밋을 off해주는 메서드
				start=true;
				sql.rollback();
				sql.TurnRepeat(start); //게임에서 턴을 반복하는 메서드
			}
			if(command==2) {
				break;
			}
		}
		}
	}