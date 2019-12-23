package test1;

public class Cellphonemain {

	public static void main(String[] args) {
		Cellphone cp = new Cellphone(); //자식클래스 객체 생성
		cp.model(); //직접적으로 부모클래스 메소드도 호출도가능
		cp.submodel(); // 자식클래스 메소드 호출 
		cp.OS(); //직접적으로 부모 클래스 메소드 호출도 가능
		cp.subOS(); //자식클래스 메소드 호출 

	}

}
