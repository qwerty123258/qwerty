package test1;
import java.util.*;
public class 예제 {

	public static void main(String[] args) {
		// 숫자 2개 입력받아 큰 수 출력
		Scanner scan = new Scanner(System.in);
		System.out.print("첫 번째 숫자를 입력하세요 : ");
		int a = scan.nextInt();
		System.out.print("두 번째 숫자를 입력하세요 : ");
		int b = scan.nextInt();
		int c = 1; 
		if (a>b) c=a; 
		else if(a<b) {c=b; System.out.print("더 큰 숫자 : " + c);}
		else System.out.print("숫자가 같습니다.");
	}

}
