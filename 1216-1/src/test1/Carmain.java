package test1;

public class Carmain {

	public static void main(String[] args) {
		//객체생성
		Car car = new Car();
//		클래스		  생성자 () :기본 생성자
		Car car2 = new Car();
		Car car3 = null; 
		car3=new Car(); // 이런 방법으로 객체 생성도 가능..
		car.color = "검정";
		System.out.println(car.color); //필드에서 지정안하고 메인에서 지정해도 반영됨.
		System.out.println(car2.color); //같은 필드를 사용하지만 다른 객체니까 당연히 null
		System.out.println(car.speed); //초기값을 따로 안줘도 기본값이 지정되어있음.
		
		System.out.println("");
		
		System.out.println(car.company); //초기값을 필드에 직접 지정하면  지정한대로 저장
		System.out.println(car2.company); // 서로 다른 객체지만 기본값이 지정되어있으므로 color 필드와는 다른 결과.
		car3.company="BMW";
		System.out.println(car3.company); // 당연히 값을 넣은 객체만 변경됨.
		
		System.out.println("");
		
		car.speed=50;
		System.out.println(car.speed+50); // 변수처럼 계산도 가능.
		
		System.out.println("");
		
		System.out.println(car.type); // 필드에 기본값을 지정하진 않았지만 생성자에 지정하면 반영이 됨.
		
		System.out.println("");
		
		Car car4= new Car("빨강",150); //매개변수가 있는 생성자 호출로 객체 생성. : 필드에 빨강,150이라는 값을 전달.
		System.out.println(car4.color);
		System.out.println(car4.speed);
		System.out.println(car4.type); //기본생성자가 호출된 경우가 아니라 null.
		System.out.println(car4.model);
		System.out.println(car4.company);
		
		System.out.println("");
		
		Car car5= new Car("은색","르노");
		System.out.println(car5.color);
		System.out.println(car5.type);
		System.out.println(car5.company);
		System.out.println(car5.speed);
		System.out.println(car5.model);
		
		System.out.println("");
		
		System.out.println(car5.toString());// 필드 출력할떄 한방에 보게 해주는 메소드.
		
		Car car6= new Car("은색", "르노",200,"SM5","휘발유");
		System.out.println("");
		System.out.println(car6.toString());
	}

}
