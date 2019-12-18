package test1;

public class Navermain {

	public static void main(String[] args) {
		Navermember nm = new Navermember("qwerty123258","1234","나","940528","남","qwerty123258@네이버","010-2808-3965");
		System.out.println(nm.getId());
		nm.setId("새로운 id");
		System.out.println(nm.getId());
	}

}