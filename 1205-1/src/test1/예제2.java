package test1;
import java.util.Scanner;
public class 예제2 {

	public static void main(String[] args) {
		//숫자 3개 입력후 큰 수 출력
		Scanner scan = new Scanner(System.in);
		System.out.println("첫번째 숫자를 입력하세요");
		int a = scan.nextInt();
		System.out.println("두번째 숫자를 입력하세요");
		int b = scan.nextInt();
		System.out.println("세번째 숫자를 입력하세요");
		int c = scan.nextInt();
		int d = 0;
		
if(a>b) //a>b가 참이면 {if(a>c) d=a; else d=c;} 실행
{if(a>c) d=a; else d=c;}
	else  // 거짓이면  {if(b>c) d=b; else d=c;} 실행
	{if(b>c) d=b; else d=c;}
System.out.println("가장 큰 숫자 : "+ d);
	}

}
