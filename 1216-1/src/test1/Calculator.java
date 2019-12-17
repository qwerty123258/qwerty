package test1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
	
	public void calpro() {
		while(true) {
		Scanner scan = new Scanner(System.in);
		int num1=0;
		int num2=0;
		int result=0;
		int command=0;
		System.out.println("--------------------------");
		System.out.println("1.덧셈 2.뺄셈 3.곱셈 4.나눗셈 5.종료");
		System.out.println("--------------------------");
		try {
			command=command(command);
		if(command==1) {
			add(num1,num2);
			}
		if(command==2) {
			subtract(num1,num2);
		}
		if(command==3) {
			result=multiply(num1,num2);
			System.out.println("계산결과 :" + result);
		}
		if(command==4) {
			result=divide(num1,num2);
			System.out.println("계산결과 :" + result);
		}
		if(command==5) {
			System.out.println("종료되었습니다");
			break;
		}
		
		}
		 catch(InputMismatchException a) {
				System.out.println("Error!!! InputMismatchException"+"\n"+"올바른 커맨드가 아닙니다. 커맨드는 숫자로 입력하셔야합니다." +" \n" + "프로그램을 다시 시작합니다." + "\n");
				System.out.println("-----------");
				System.out.println("재시작 중");
				System.out.println("-----------"+ "\n");
	}
		}
		
	}
	
	public void add(int num1,int num2) {
		Scanner scan = new Scanner(System.in);
		System.out.println("계산할 숫자를 입력하세요.");
		num1=scan.nextInt();
		System.out.println("계산할 숫자를 입력하세요.");
		num2=scan.nextInt();
		int result=num1+num2;
		System.out.println("덧셈 결과 :" +result);
	}
	private void subtract(int num1,int num2) {
		Scanner scan = new Scanner(System.in);
		System.out.println("계산할 숫자를 입력하세요.");
		num1=scan.nextInt();
		System.out.println("계산할 숫자를 입력하세요.");
		num2=scan.nextInt();
		int result=num1-num2;
		System.out.println("뺄셈 결과 :"+result);
	}
	
	private int multiply(int num1,int num2) {
		Scanner scan = new Scanner(System.in);
		System.out.println("계산할 숫자를 입력하세요.");
		num1=scan.nextInt();
		System.out.println("계산할 숫자를 입력하세요.");
		num2=scan.nextInt();
		int result=num1*num2;
		return result;
	}
	private int divide(int num1,int num2) {
		Scanner scan = new Scanner(System.in);
		System.out.println("계산할 숫자를 입력하세요.");
		num1=scan.nextInt();
		System.out.println("계산할 숫자를 입력하세요.");
		num2=scan.nextInt();
		int result=num1/num2;
		return result;
		
	}
	private int command(int command) {
		Scanner scan = new Scanner(System.in);
		System.out.println("커맨드를 입력하세요.");
		command=scan.nextInt();
		return command;
	}
	
	
	
}
	
