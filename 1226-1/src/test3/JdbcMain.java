package test3;

import java.util.List;
import java.util.Scanner;

public class JdbcMain {

	public static void main(String[] args) {
		DBsql sql = new DBsql(); //쿼리문이 저장되어있는 객체 생성
		List<Student> stuList = null;
		Scanner scan = new Scanner(System.in);
		for(;;) {
			System.out.println("0.db 접속 1.조회 2.조회(2)  3.추가   4.입력하여 데이터 추가(1)  5.입력하여 데이터 추가 (2) 6.입력하여 데이터 추가 (3) 7.삭제  8.종료");
		int command=scan.nextInt();
		if(command==0) {
		sql.dbConnection(); 
		}
		if(command==1) {
		sql.selectDB(); //데이터 조회하기
		}
		if(command==2) {
		stuList=sql.selectDB2(); //데이터 조회한것을 리스트에 넣은뒤 출력하기.
		for(int i=0; i<stuList.size(); i++) {
			System.out.println(stuList.get(i).toString());	
		}
		}
		if(command==3) {
			sql.insertDB(); // 입력해놓은 쿼리문으로 데이터 추가하기.
		}
		if(command==4) {
			sql.insertDB2(); // 쿼리문에 틀만 입력해놓은 후에 데이터를 입력하여 추가하기.
		}
		if(command==5) {
			sql.insertDB3(); // 입력해서 데이터 추가하기.
		}
		if(command==6) {
			sql.insertDB4(); // 쿼리문 전체를 입력해서 데이터 추가하기.
		}
		if(command==7) {
			sql.deleteDB(); //입력해놓은 쿼리문으로 데이터 삭제하기.
		}
		if(command==8) {
			break;
		}
		}
		
	}

}
