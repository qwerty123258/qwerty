package test3;

public class 랜덤메소드 {

	public static void main(String[] args) {
//		특정 범위에서 숫자 하나를 출력해주는 메소드.
//		1~10 사이의 숫자.
//		(int)(Math.random()*10) + 1
//		Math.random() : 0<=n<1.0

		int a;
		a=(int)(Math.random() * 6) + 1;
		if (1==(int)(Math.random() * 6) + 1) System.out.println(a+"이 나왔습니다.");
		else if (2==(int)(Math.random() * 6) + 1) System.out.println(a+"가 나왔습니다.");
		else if (3==(int)(Math.random() * 6) + 1) System.out.println(a+"이 나왔습니다.");
		else if (4==(int)(Math.random() * 6) + 1) System.out.println(a+"가 나왔습니다.");
		else if (5==(int)(Math.random() * 6) + 1) System.out.println(a+"가 나왔습니다.");
		else System.out.println(a+"이 나왔습니다.");
		
		int b;
		b=(int)(Math.random() * 6) + 1;
		if (b==1) System.out.println(b+"이 나왔습니다.");
		else if (b==2) System.out.println(b+"가 나왔습니다.");
		else if (b==3) System.out.println(b+"이 나왔습니다.");
		else if (b==4) System.out.println(b+"가 나왔습니다.");
		else if (b==5) System.out.println(b+"가 나왔습니다.");
		else System.out.println(b+"이 나왔습니다.");

	}

}
