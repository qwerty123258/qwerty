package test1;

import java.util.Scanner;

public class 배열연습 {

	public static void main(String[] args) {
		// 크기가 5인 배열 선언 후 값을 넣고 출력
		double ar[] = {23.456, 0.234545, 435.3456,456.4556,3.141592};
		for(int i=0; i<ar.length; i++) {
			
			System.out.println(ar[i]);
		}
		//크기가 3인 배열을 선언하고 스캐너를 이용하여 값을  3개 저장후 입력된 값의 총합,평균 계산.
		double avg=0;
		double sum=0;
		double ar2[] =new double[3];
		Scanner scan = new Scanner(System.in);
		for(int i=0; i<ar2.length; i++) {
		ar2[i] = scan.nextDouble();
		sum+=ar2[i];
		avg=sum/ar2.length;
		}
		System.out.println(sum);
		System.out.println(avg);
		
		scan.close();
		

	}

}
