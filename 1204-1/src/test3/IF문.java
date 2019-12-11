package test3;
public class IF문 {

	public static void main(String[] args) {
//if(조건식) 조건이 true 일때 else 조건이 false 일때
//		if(조건식 a) 조건식 a가 true일때 else if(조건식 b) a가 false이고 조건식 b가 true 일때 else 모두 false 일때
		boolean condition;
		condition = true;
		if(condition) {System.out.println("조건이 만족");}
		else {System.out.println("조건이 불만족");}
		
		int num1=5,num2=3;
		if(num1>num2) {System.out.println("ㅇㅇ");}
		else {System.out.println("ㄴㄴ");}
		
		num1=5;
		num2=3;
		if(num1<num2) {System.out.println("ㅇㅇ");}
		else {System.out.println("ㄴㄴ");}
		
		int grade = 90;
		if(grade>=90) System.out.println("A학점입니다.");
		else System.out.println("B학점입니다.");

	}

}
