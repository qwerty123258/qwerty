package test1;

public class Computer extends Calculator {
	@Override //메서드 재정의(이름은 유지하고 내용은 바꾸는 경우), @Annotation : 주석?
	public int calculation(int a, int b) { //부모클래스와 자식클래스의 메소드이름이 같음.
		System.out.println("부모 메서드 결과"+super.calculation(a, b));
		int c=(a+b)/2;
		return c;
	}
}
