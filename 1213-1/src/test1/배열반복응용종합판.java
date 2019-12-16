package test1;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class 배열반복응용종합판 {

	public static void main(String[] args) {
		int command = 0;//명령 입력용 변수
		int scorecommand=0; // 점수 입력용 변수
		double avg=0;// 평균 출력용 변수
		int scoresum = 0;//평균 계산용 변수
		int max=Integer.MIN_VALUE; //최고점수 출력용 변수
		int studentcount=0; // 학생 수 초기 값.
		Integer student[]=null; //배열 선언
		Scanner scan = new Scanner(System.in);
		int error=0; // 학생을 등록안하고 점수입력이나 다른 커맨드 사용시 에러 방지용 변수.
		int error1=0; // 점수를 입력안하고 리스트열람이나 다른 커맨드 사용시 에러 방지용 변수.
		
		while(true) {
			System.out.println("---------------------------------------");
			System.out.println("1.학생 수 |2.점수입력 |3.점수리스트 |4.분석 |5.종료");
			System.out.println("---------------------------------------");
			System.out.println("커맨드를 입력하세요.");
			try {
			command=command(command);
			
		if(command==1) {
			student = new Integer[studentcount(studentcount)]; // studentcount메서드에서 반환된 값을 사용.
			error++;
			}
		
		if(command==2 && error>0) { 
			for(int i=0; i<student.length; i++) {
				scorecommand=score(scorecommand);//score 메서드에서 반환된 값 사용.
				student[i]=scorecommand;
				scoresum+=scorecommand; //점수를 2번 3번 입력해버리면 sum이 누적되버림.
				avg=(double)scoresum/student.length; //그래서 평균 계산할때 문제 생김...
				error1++;
				if(max<scorecommand) {
					max=scorecommand;
				}
			}
		}
			else if(command==2 && error==0) {
				System.out.println("등록된 학생이 없어서 점수 입력을 할 수 없습니다."+"\n");
			}
		try {
		if(command==3 && error>0 && error1>0 && student[0]>1) {
			System.out.println(student.length+"명의 점수 리스트입니다."+"\n");
			for(int i=0; i<student.length; i++) {
		        Arrays.sort(student,Collections.reverseOrder()); //내림차순 사용시
				System.out.println(student[i]);
			}
		}
		else if(command==3 && error==0) {
			System.out.println("등록된 학생이 없어 리스트를 볼 수 없습니다."+"\n");
		}
		else if(command==3) {System.out.println("점수 입력을 하지 않아  리스트를 볼수 없습니다."+"\n");
		}
		
		if(command==4 && error>0 && error1>0 && student[0]>1) {
			System.out.println(student.length+"명의 분석결과입니다.");
			System.out.println("최고점수 :" +max);
			System.out.println("평균점수 :" +avg);
			for(int i=0; i<student.length; i++) {
		        Arrays.sort(student,Collections.reverseOrder()); //내림차순 사용시
				System.out.println(student[i]);
				}
			}
		else if(command==4 && error==0) {
			System.out.println("등록된 학생이 없어 분석이 불가능합니다."+"\n");
		}
		else if(command==4) {System.out.println("점수 입력을 하지 않아 분석이 불가능합니다."+"\n");
		}
		}
		catch(Exception e) {
			System.out.println("등록된 학생이 바뀌었습니다.");
		}
		if(command==5) {
			System.out.println("종료되었습니다.");
			break;
		}
		
		if(command!=1 && command!=2 && command!=3 && command!=4 && command!=5) {
			 System.out.println("유효한 커맨드가 아닙니다."+"\n"); 
		}
	}
			 catch(InputMismatchException a) {
					System.out.println("올바른 커맨드가 아닙니다. 커맨드는 숫자로 입력하셔야합니다." +" \n" + "프로그램을 다시 시작합니다." + "\n");
					System.out.println("-----------");
					System.out.println("재시작 중");
					System.out.println("-----------"+ "\n");
					
		}
		
	}

		
	scan.close();
		
		}
	
	@SuppressWarnings("resource")
	private static int score(int scorecommand) { //scorecommand라는 변수를 가져오면서 메서드 생성.
		int scoresum = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("학생의 점수를 입력하세요.");
		scorecommand=scan.nextInt();
		return scorecommand;//입력된 점수 반환
		}
	
	@SuppressWarnings("resource")
	private static int command(int command) { //command라는 변수를 가져오면서 메서드 생성.
		Scanner scan = new Scanner(System.in);
		command=scan.nextInt();
		return command;//입력된 커맨드 반환
		}
	
	@SuppressWarnings("resource")
	private static Integer studentcount(int studentcount) {//  studentcount라는 변수를 가져오면서 메서드 생성.
		Scanner scan = new Scanner(System.in);
		System.out.println("등록할 학생 수를 입력하세요.");
		studentcount=scan.nextInt();
		System.out.println("등록한 학생 수는 " +studentcount+"명입니다."+"\n");
		return studentcount; //입력한 학생수 반환.
	}
}

