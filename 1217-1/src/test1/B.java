package test1;

public class B {
	public static void main(String[] args) {
		A a = new A();
		System.out.println(a.field1); // public이라 가능
		System.out.println(a.field2); //protected 마찬가지로 같은 패키지라서 가능
		System.out.println(a.field3); // 따로 지정하지 않은 default라서 같은 패키지, 고로 가능.
//		System.out.println(a.field4);  //필드 4는 접근 제한자가 private이므로 클래스가 달라서 호출이 불가능.
		a.hello();
		a.hello1();
		a.hello2();
//		a.hello3(); //필드와 마찬가지로 private이므로 메소드도 호출 불가능.
	}
}
