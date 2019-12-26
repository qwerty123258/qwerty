package test3;

import java.sql.Connection;
import java.util.Scanner;

public class JdbcMain {

	public static void main(String[] args) {
		Connection con=null; //Connection 타입의 변수 선언
		con=DBConnection.MakeConnection(); //연결 결과를 con에 저장.
		DBsql sql = new DBsql(); //쿼리문이 저장되어있는 객체 생성
		Scanner scan = new Scanner(System.in);
		for(;;) {
			System.out.println("1.조회  2.추가   3.입력하여 데이터 추가  4.삭제  5.종료");
		int command=scan.nextInt();
		if(command==1) {
		sql.selectDB(con); //저장된 con을 매개변수로 하는 쿼리문이 저장된 메서드 실행.
		}
		if(command==2) {
			sql.insertDB(con); // 데이터 추가하기.
		}
		if(command==3) {
			sql.insertDB2(con); // 입력해서 데이터 추가하기.
		}
		if(command==4) {
			sql.deleteDB(con); //추가한 데이터 삭제하기.
		}
		if(command==5) {
			break;
		}
		}
		
	}

}
