package test1;

public class A {
	//접근 제한자 public,private,protected,default
	// 필드,생성자,메서드에는 다 적용가능하고, 클래스의 경우는 public이나 default만 가능.
	// public : 모든 곳에 다 가능. private: 같은 클래스내에서만 호출가능. default: 같은 패키지에서는 접근 가능. protected : 상속받은 클래스까지 접근 가능.
	public int field1;
	protected static int field2;
	int field3;
	private int field4;
	
	public void hello() {
		System.out.println("public 접근 되었음");
	}
	protected void hello1() {
		System.out.println("protected 접근 되었음");
	}
	  void hello2() {
		System.out.println("default 접근 되었음");
	}
	private void hello3() {
		System.out.println("private 접근 되었음");
	}
}
