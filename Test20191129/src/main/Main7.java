package main;
public class Main7 {
	public static void main(String[] args) {
		// 랜덤메소드와 반복문 활용해서 로또번호 6개 출력 3회 하기(단, 중복되면 안됨)
		int num1;
		int num2;
		int num3;
		int num4;
		int num5;
		int num6;
		for(int i=1; i<=3; i++) {
		for(;;) { // 무제한 반복
		num1 = (int)(Math.random() * 45) +1;
		num2 = (int)(Math.random() * 45) +1;
		num3 = (int)(Math.random() * 45) +1;
		num4 = (int)(Math.random() * 45) +1;
		num5 = (int)(Math.random() * 45) +1;
		num6 = (int)(Math.random() * 45) +1;
		
		if(num1!=num2 && num1!=num3 && num1!=num4 && num1!=num5 && num1!=num6) { // num1이 나머지 숫자와 다를때
			if(num2!=num3 && num2!=num4 && num2!=num5 && num2!=num6) { //num2가 나머지 숫자와 다를때
				if(num3!=num4 && num3!=num5 && num3!=num6) {//num3이 나머지 숫자와 다를때
					if(num4!=num5 && num4!=num6) { //num4가 나머지 숫자와 다를때
						if(num5!=num6) { //num5가 나머지 숫자와 다를때,  중첩 조건을 5개만족해야 출력이 되므로 중복 없음.
							System.out.println(num1 + " " +num2 + " " +num3 + " " +num4 + " " +num5 + " " +num6 + "\t"); break;
						}
					}
				}
			}
				
		}
		}
	}
	}
}
		


