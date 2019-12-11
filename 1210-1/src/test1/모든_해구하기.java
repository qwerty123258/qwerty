package test1;

public class 모든_해구하기 {

	public static void main(String[] args) {
		// 4x+5y=60 을 만족하는 모든 해를 구하시오
		for(int x=0; x<=60; x++) {
			for(int y=0; y<=60; y++) {
		if((4*x) +(5*y)==60) System.out.println("x값 :"+ x  +"," + "y값 :" + y);
		}
	}
	}

}
