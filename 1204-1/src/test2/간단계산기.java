package test2;
import java.util.Scanner; // 스캐너라는 클래스 호출
public class 간단계산기 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
		int number1,number2,number3;
		System.out.print("숫자를 입력하세요 : ");
		number1= scan.nextInt();
		System.out.print("더할 숫자를 입력하세요 : ");
		number2 = scan.nextInt();
		
		number3= number1 + number2;
		System.out.println("입력한 숫자: " + number1);
		System.out.println("더할 숫자 : " + number2);
		System.out.println("계산결과 : " + number3);

	}

}
