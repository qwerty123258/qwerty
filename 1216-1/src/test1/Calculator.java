package test1;


public class Calculator {
	
	
	public int sum(int num1, int num2) { //반환이 있는 메소드 int형으로 반환
		int result; 
		
		result=num1+num2; // 메인에서 가져온 num1과 num2를 바탕으로 연산후
		
		return result; // 결과를 메인으로 리턴함
		
	}
	
	public int sub(int num1, int num2) {
		int result;
		
		result=num1-num2;
		
		return result;
		
	}
	
	public void sum1(int num1, int num2) { //반환값이 없는 메소드 void
		int result;
		
		result=num1+num2;
		
		System.out.println("리턴 값 : " + result);
		
	}
	public void add(int num1,int num2) {
		int result=num1+num2;
		System.out.println("덧셈 결과 :" +result);
	}
	public void subtract(int num1,int num2) {
		int result=num1-num2;
		System.out.println("뺄셈 결과 :"+result);
	}
	
	public int multiply2(int num1,int num2) {
		int result=num1*num2;
		return result;
	}
	public int divide2(int num1,int num2) {
		int result=num1/num2;
		return result;
	}
	
	
	
}
	
