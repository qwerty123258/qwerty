package test1;

import java.util.Scanner;

public class 배열max {

	public static void main(String[] args) {
		// 배열에 정수 5개 입력받아 가장 큰 수를 출력
		Scanner scan = new Scanner(System.in);
		int ar[] = new int[5];
		int num=0; //스캐너 입력 변수
		int max=Integer.MIN_VALUE; //최대값 변수, 무슨 수를 입력하건 디폴트 값이 가장 최소로 선언해두면 음수건 양수건 다 비교가능.

		for(int i=0; i<ar.length; i++) {
			num = scan.nextInt();
			ar[i] = num;
			if(ar[i]>max) {
				max=ar[i];
			}
		}
		System.out.println(max);
		scan.close();
	}

}
