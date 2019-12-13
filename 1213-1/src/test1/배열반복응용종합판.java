package test1;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 배열반복응용종합판 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int command = 0;//명령 입력용 변수
		int scorecommand=0; // 점수 입력용 변수
		int studentcount=0; // 학생수 입력용 변수
		int savescore=0; // 점수 내림차순 계산용 변수
		double avg=0;// 평균 출력용 변수
		int scoresum=0;//평균 계산용 변수
		int count=0; //평균 계산용 변수
		int max=0; //최고점수 출력용 변수
		int error=0;//예러 방지용 변수,
		//사용 이유 : 1번 커맨드를 진행하지 않고 2,3,4번의 커맨드를 입력할시 등록한 학생이 없으므로 에러가 뜬다. 
		// 에러를 없애기위하여 1번의 과정을 거처야만 값이 생기는 변수인 error의 값을 비교하는 조건과 입력한 커맨드라는 총 2개의 조건으로 프로그램을 실행한다.
		Integer student[]=null;
		while(true) {
			System.out.println("커맨드를 입력하세요.");
			System.out.println("---------------------------------------");
			System.out.println("1.학생수 |2.점수입력 |3.점수리스트 |4.분석 |5.종료");
			System.out.println("---------------------------------------");
			command=scan.nextInt();
		if(command==1) {
			System.out.println("등록할 학생수를 입력하세요.");
			studentcount=scan.nextInt();
			student = new Integer [studentcount]; 
			System.out.println("등록한 학생수는 " +studentcount+"명입니다.");
			error++;
			}
		if(command==2 && error>1) 
		for(int i=0; i<student.length; i++) {
			System.out.println((i+1)+"번째 학생의 점수를 입력하세요.");
			scorecommand=scan.nextInt();
			student[i]=scorecommand;
			scoresum+=scorecommand;
			count++;
			max=scorecommand;
			avg=(double)scoresum/count;
			if(max<scorecommand) {
				max=scorecommand;
			}
		}
		if(command==3 && error>1) {
			System.out.println(student.length+"명의 점수 리스트입니다.");
			for(int i=0; i<student.length; i++) {
			System.out.println(student[i]);
			}
		}
		if(command==4 && error>1) {
			System.out.println(student.length+"명의 분석결과입니다.");
			System.out.println("최고점수 :" +max);
			System.out.println("평균점수 :" +avg);
			for(int i=0; i<student.length; i++) {
//				for(int j=i+1; j<student.length; j++) { //여긴 정공법.
//					if(student[i]<student[j]) {
//						savescore=student[j];
//						student[j]=student[i];
//						student[i]=savescore;
//					}
//						
//				}
		        Arrays.sort(student,Collections.reverseOrder()); //내림차순 사용시
				System.out.println(student[i]);
			}
		}
		if(command==5) {
			System.out.println("종료되었습니다.");
			break;
		
		}
		if(command!=1 && command!=2 && command!=3 && command!=4 && command!=5) {
			System.out.println("유효한 커맨드가 아닙니다.");
		}
		}

	}
}

