package test3;
import java.util.Scanner;
public class IF문스캐너 {

	public static void main(String[] args) {
		int grade;
		Scanner scan = new Scanner(System.in);
		System.out.print("점수를 입력하세요 : ");
		grade=scan.nextInt();
		if(grade>=90) System.out.println("A학점입니다.");
		else System.out.println("B학점입니다.");
		
		
		int grade1;
		Scanner scan1 = new Scanner(System.in);
		System.out.print("점수를 입력하세요 : ");
		grade1=scan1.nextInt();
		if(grade1 >=90) System.out.println("A학점입니다.");
		else if(grade1>=80) System.out.println("B학점입니다.");
		else if(grade1>=70) System.out.println("C학점입니다.");
		else if(grade1>=60) System.out.println("D학점입니다.");
		else System.out.println("F학점입니다.");

	}

}
