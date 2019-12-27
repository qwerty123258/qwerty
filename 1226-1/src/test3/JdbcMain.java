package test3;

import java.util.Scanner;

public class JdbcMain {

	public static void main(String[] args) {
		DBsql sql = new DBsql(); //쿼리문이 저장되어있는 객체 생성
		Student stu = new Student();
		Scanner scan = new Scanner(System.in);
		for(;;) {
			System.out.println("0.db 접속 1.조회  2.추가   3.입력하여 데이터 추가(1)  4.입력하여 데이터 추가 (2) 5.입력하여 데이터 추가 (3) 6.삭제  7.종료");
		int command=scan.nextInt();
		if(command==0) {
		sql.dbConnection(); //저장된 con을 매개변수로 하는 쿼리문이 저장된 메서드 실행.
		}
		if(command==1) {
		sql.selectDB(); //저장된 con을 매개변수로 하는 쿼리문이 저장된 메서드 실행.
		}
		if(command==2) {
			sql.insertDB(); // 데이터 추가하기.
		}
		if(command==3) {
			sql.insertDB2(); // 입력해서 데이터 추가하기.
		}
		if(command==4) {
			sql.insertDB3(stu); // 입력해서 데이터 추가하기.
		}
		if(command==5) {
			sql.insertDB4(); // 입력해서 데이터 추가하기.
		}
		if(command==6) {
			sql.deleteDB(); //추가한 데이터 삭제하기.
		}
		if(command==7) {
			break;
		}
		}
		
	}

}
