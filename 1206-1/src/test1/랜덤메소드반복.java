package test1;
public class 랜덤메소드반복 {

	public static void main(String[] args) {
//		특정 범위에서 숫자 하나를 출력해주는 메소드.
//		1~10 사이의 숫자.
//		(int)(Math.random()*10) + 1
//		Math.random() : 0<=n<1.0
		//주사위 2개를 굴려서 그 수의 합이 5면 반복을 종료
		boolean six=true; 
		while(six) {
		int num1;
		num1=(int)(Math.random() * 6) + 1;
		int num2;
		num2=(int)(Math.random() * 6) + 1;
		int sum=num1+num2;
		System.out.print("주사위 1 숫자:" + num1);
		System.out.print("   ");
		System.out.println("주사위 2 숫자:" + num2);
		if(sum == 5) {six=false; System.out.println("두 주사위의 합이 " + sum+"가 나왔네요 종료합니다.");}
		}
		//주사위 1개를 굴려서 수가 6이 나오면 반복 종료
		//앞에 반복에서 six 변수에 false 값이 대입된채로 끝났기때문에 six에 true를 다시 대입 후 반복 시작
		six=true;
		while(six) {
		int num1;
		num1=(int)(Math.random() * 6) + 1;
		System.out.println("주사위 1 숫자:" + num1);
		if(num1==6) {six=false; System.out.println(num1+"이 나왔네요 종료합니다.");}
		}
		
	}

}
