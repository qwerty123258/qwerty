package test2;

public class Sedan extends Car {
	@Override
	public void license() {
		System.out.println("보통면허로 운전가능.");
		
	}
	@Override
	public void fuel() {
		System.out.println("대체로 휘발유");
	}

}
