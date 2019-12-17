package test1;

import java.util.Scanner;

public class Calculator {
	public void calpro() {
		Scanner scan = new Scanner(System.in);
		System.out.println("숫자를 입력하세요.");
		int num1 = scan.nextInt();
		System.out.println("숫자를 입력하세요.");
		int num2 = scan.nextInt();
		int result=add(num1,num2);
		double avg=avg(result);
		System.out.println("평균값은 ? " +avg + "입니다.");

		scan.close();
		
	}
	
	public int add(int num1,int num2) {
		int result=0;
		
		result=num1+num2;
			
		return result;
		
	}
	
	public double avg(int result) {
		double avg=0;
		
		avg=(double)result/2;
		
		return avg;
		
	}


}
