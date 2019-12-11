package main;
import java.util.*; //스캐너 객체를 사용해야하므로 import
public class Main3 {

	public static void main(String[] args) {
		// 숫자를 입력해서  입력한 숫자의 값 만큼 */**/***/****/*****/* x n을 출력하기
		Scanner scan = new Scanner(System.in); //스캐너 객체 생성
		int inputnum = scan.nextInt(); // nextInt() 실행결과를 inputnum 변수 선언후 대입.
		for(int i=1; i<=inputnum; i++) { //입력한 inputnum과 같을때까지 반복.
			// 4를 입력했다면 4번 반복. (줄바꿈을 총 4번 하라는 것,행의 갯수가 4개라는 것)
			for(int j=1; j<=i; j++){ //j가 i와 같아질때까지 반복.
			System.out.print("*");	// i가 1이면  외부 for문 이후 내부 for문에서 j는 1번 반복  *은 1개만 출력.
									// 공백이 출력되고 나면 i가 1이 증가했으므로 내부 for문은 j가 i와 갈아 질때까지 2번 반복
			
			}
			System.out.println(""); // * 출력후 줄바꿈 의미로 공백 출력
		}
		

	}

}
