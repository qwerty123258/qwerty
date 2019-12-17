package test1;

public class Staticmain {

	public static void main(String[] args) {
		Static st= new Static();
		System.out.println(st.pi); // static으로 선언된 필드. 가능하지만 static 방식으로 호출을 하지 않아서 노란 밑줄.
		System.out.println(st.pi1); //static이 아닌 필드
		System.out.println(Static.pi); //객체화 안해도 static은 호출 가능.

		System.out.println(st.PI); //변수가 아닌 상수도 마찬가지로 같은 방식으로 호출해야함.
		System.out.println(Static.PI);
		
		System.out.println(st.add(5,5));
		System.out.println(st.subtract(5,6)); //필드가 아닌 메서드도 마찬가지.
		System.out.println(Static.subtract(5,6));
	}

}
