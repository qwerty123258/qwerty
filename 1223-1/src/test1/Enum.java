package test1;

public class Enum {
	enum season {Spring,Summer,Fall,Winter}; //enum 선언
	public static void main(String[] args) {
		season season1,season2,season3,season4;
		season1=season.Spring; //enum 사용하기,다른 값은 사용불가.
		season2=season.Summer;
		season3=season.Fall;
		season4=season.Winter;
		System.out.println(season1);
		System.out.println(season2);
		System.out.println(season3);
		System.out.println(season4);
		System.out.println("");
		System.out.println(season.valueOf("Spring")); //열거한 특정값 출력시.
		System.out.println("");
		for(season a:season.values()) {
			System.out.println(a); //열거한것 전부 출력시
		}
	}

}
