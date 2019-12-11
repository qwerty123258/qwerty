package main;
import java.util.*;
public class Main {

	public static void main(String[] args) {
//		세 수를 입력하여 2번째로 큰 수를 출력하기.
//		1.세개의 정수 변수 선언
//		2.입력을 받는 객체를 생성한다.
//		3.각 변수에 입력한 값을 대입한다.
//		4. 조건에 맞게 출력한다.
		int num1,num2,num3;
		Scanner scan = new Scanner(System.in);
		num1=scan.nextInt();
		num2=scan.nextInt();
		num3=scan.nextInt();
		// num1가 2번째로 큰 경우, num2가 2번째로 큰 경우, num3가 2번째로 큰 경우
		// num1 2번째로 큰 경우 =  num 1 기준잡아 비교하기. num2>=num1 and num1>=num3 or num3>=num1 and num1>=num2
		// num2 2번째로 큰 경우 =  num 2 기준잡아 비교하기. num1>=num2 and num3>=num1 or num3>=num2 and num2>=num1
		// num3 2번째로 큰 경우 =  num 3 기준잡아 비교하기. num1>=num3 and num3>=num2 or num2>=num3 and num3>=num1 
		if(num2>=num1 && num1>=num3 || num3>=num1 && num1>=num2) {System.out.println(num1);}
		else if(num1>=num2 && num2>=num3 || num3>=num2 && num2>=num1) {System.out.println(num2);}
		else System.out.println(num3);
		
	}
}
