package test1;

import java.util.Scanner;

public class CalculatorMain {

	public static void main(String[] args) {
		//계산기 메소드 활용해보기.
		Scanner scan = new Scanner(System.in);
		int num1=9;
		int num2=5;
		int result=0;
		int command=0;
		Calculator ca = new Calculator();
		ca.sum(num1, num2);
		System.out.println("더하기 결과 : " +ca.sum(num1, num2));
		ca.sub(num1, num2);
		System.out.println("빼기 결과 : " + ca.sub(num1, num2));
		ca.sum1(num1, num2);
		while(true) {
			System.out.println("--------------------------");
			System.out.println("1.덧셈 2.뺄셈 3.곱셈 4.나눗셈 5.종료");
			System.out.println("--------------------------");
			System.out.println("커맨드를 입력하세요.");
			command=scan.nextInt();
			if(command==1) {
				System.out.println("계산할 숫자를 입력하세요.");
				ca.add(scan.nextInt(),scan.nextInt());}
			if(command==2) {
				System.out.println("계산할 숫자를 입력하세요.");
				ca.subtract(scan.nextInt(),scan.nextInt());
			}
			if(command==3) {
				System.out.println("계산할 숫자를 입력하세요.");
				result=ca.multiply2(scan.nextInt(),scan.nextInt());
				System.out.println("계산결과 :" + result);
			}
			if(command==4) {
				System.out.println("계산할 숫자를 입력하세요.");
				result=ca.divide2(scan.nextInt(),scan.nextInt());
				System.out.println("계산결과 :" + result);
			}
			if(command==5) {
				System.out.println("종료되었습니다");
				break;
			}
			
		}
		
		

	}


}
