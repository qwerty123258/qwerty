package test1;

public class Dog extends Animal {
	
	@Override
	public void sound() { //추상 메소드 재정의 >>추상메소드에서 못채운 내용을 채워넣어라. 라는 뜻.
		System.out.println("멍멍");
	}
	
	@Override
	public void walking() {
		System.out.println("네발로 걸어다닌다.");
		
	}
}
