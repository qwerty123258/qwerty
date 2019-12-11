package test1;
import java.util.Scanner;
public class Java에재 {

	public static void main(String[] args) {
//		합격 불합격 출력 프로그램
//		1. 입력값 : 학년, 점수
//		2. 조건
//		1~3학년은 점수가 60점 이상이어야 "합격입니다." 출력
//		4학년은 70점 이상이어야 "합격입니다." 출력
		int score;
		int grade;
		Scanner scan = new Scanner(System.in);
		System.out.print("점수를 입력하세요.");
		score = scan.nextInt();
		System.out.print("학년을 입력하세요.");
		grade = scan.nextInt();
		if (grade<4 && score>=60) System.out.print("합격입니다.");
		else if (grade>3 && score>=70) System.out.print("합격입니다.");
		else System.out.print("불합격입니다.");

	}

}
