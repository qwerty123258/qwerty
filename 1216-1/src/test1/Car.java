package test1;

public class Car {
	//필드(field),생성자(Constructor),메소드(method)
//	1. field : 데이터 저장 공간
//	2. Constructor : 객체를 만들때 따라야하는 규칙을 정의한다. 객체 생성시 필요한 매개변수를 규정하고 매개변수는 있을 수도 없을 수도 있다. 
//	매개변수가 없으면 기본 생성자, 생정자의 이름은 클래스 이름과 동일.
// ex) 매개변수는 스캐너 클래스에선 System.in 이라고 보면 됨.
//	3. method :특정 기능묶음,매개변수는 없을수도 있고 반환값 존재.
	
	//아래 요런것들이 필드.
	String color;
	int speed;
	String type;
	String model;
	String company="도요타";
	
	//기본 생성자 선언
	Car(){
//		필드값을 세팅하거나 매개변수로 넘겨받은 값을 필드값으로 세팅
		type="하이브리드";
	}
	//매개변수가 있는 생성자 선언.
	Car(String color, int speed) {
		this.color=color; //메인에서 넘겨 받은 값을 필드로 보냄
		this.speed=speed; //this의 용도는 필드가 이것이다 라는것을 명시하기 위함.
	}
	
	Car(String color, String company) { //overriding : 생성자를 중복 사용. overloading : 상속과 관련.
		this.color=color;
		this.company=company; 
	}
	Car(String color, String company,int speed,String model,String type) {
		this.color=color;
		this.company=company; 
		this.speed=speed;
		this.model=model;
		this.type=type;
	}
	@Override
	public String toString() {
		return "Car [color=" + color + ", speed=" + speed + ", type=" + type + ", model=" + model + ", company="
				+ company + "]";
	}
}
