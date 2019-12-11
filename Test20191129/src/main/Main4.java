package main;
import java.util.*; //스캐너 객체를 사용해야 하므로 import
public class Main4 {

	public static void main(String[] args) {
		// 입력한 숫자부터 * 을 출력하기, * x n/*****/****/***/~~~/* 의 형태.
		Scanner scan =new Scanner(System.in); //스캐너 객체 생성
		int inputnumber= scan.nextInt(); //nextInt() 실행결과를 inputnumber에 대입
		for(int i=1; i<=inputnumber; i++) {// 입력한 inputnumber만큼 반복 4를 입력하면 4번 반복, 즉 줄 바꿈 4번해라
			for(int j=inputnumber; j>=i; j--) {// j에 입력한 inputnumber를 대입하고 선언, j(입력한 inputnumber)가  감소하면서 i와 같아질때까지 반복.
				System.out.print("*"); // 입력숫자가 1이라면 i는 1로 같기때문에 한 번만 반복해서 *은 1개만 출력.
				// 공백이 출력되었다면 i는 1이 증가해서 2이고 j는 1이 감소하여 0이기 때문에  * 은 출력되지 않음.
				// 입력숫자를 4로 입력했다면 4가 1과 같아질때까지 *을 4번 출력하고 외부 for문의 공백이 출력이 됨.
				// 공백이 출력되 었으면 i는 2이고 j는 4이므로 4가 2와 갈아질때까지 *을 3번만 출력한다.
			}
			System.out.println(""); //내부 for문의 반복이 끝나면 줄바꿈의 의미로 공백을 출력
		}
	}

}
