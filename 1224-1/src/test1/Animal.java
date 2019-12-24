package test1;

public abstract class Animal {// 추상 클래스
//	1. 자식클래스(실체클래스)의 규격을 정한다. >> 메소드의 틀을 정해줌.(생성자처럼)
//	-메소드 재정의 가능
//	-추상 메소드 정의 가능.(이때의 경우에는 반드시 자식메소드에서 재정의해야함.)
	String kind;
	
	public void breath() {
		System.out.println("숨을 쉰다.");
	}
	public abstract void sound(); //추상메소드 선언.
	public abstract void walking();
}
