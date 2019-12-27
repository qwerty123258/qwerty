package test1;

import java.util.List;
import java.util.Scanner;

public class Studentmain {

	public static void main(String[] args) {
		DBsql sql = new DBsql(); //쿼리문이 저장되어있는 객체 생성
		List<Student> stuList = null;
		Scanner scan = new Scanner(System.in);
		for(;;) {
			System.out.println("0.db 접속 1.조회  2.추가   3.수정하기  4.삭제하기 5.종료");
		int command=scan.nextInt();
		if(command==0) {
		sql.dbConnection(); 
		}
		if(command==1) {
			stuList=sql.selectDB(); //데이터 조회하기
			for(int i=0; i<stuList.size(); i++) {
				System.out.println(stuList.get(i).toString());	
			}
		}
		if(command==2) {
			sql.insertDB(); //데이터 입력해서 추가하기.
		}
		if(command==3) {
			sql.editDB(); //특정 학생의 전화번호 수정하기.
		}
		if(command==4) {
			sql.deleteDB(); //특정 학생의 데이터 삭제하기
		}
		if(command==5) {
			break;
		}
		}

	}

}
