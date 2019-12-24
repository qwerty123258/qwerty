package test2;

public class Truck extends Car {

	@Override
	public void license() {
		System.out.println("대형면허로 운전가능");
		
	}
	@Override
	public void fuel() {
		System.out.println("대체로 경유");
	}

}
