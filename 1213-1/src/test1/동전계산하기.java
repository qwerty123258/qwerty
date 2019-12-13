package test1;

import java.util.Scanner;

public class 동전계산하기 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int money=0;
		int inputmoney=0;
		while(true) {
			System.out.print("금액입력 :");
		money=scan.nextInt();
		System.out.println("500원짜리 :"+money/500 +"개");
		System.out.println("100원짜리 :"+money%500/100 +"개");
		System.out.println("50원짜리 :"+money%500%100/50 +"개");
		System.out.println("10원짜리 :"+money%500%100%50/10 +"개");
		break;
		}
		
		int[] coin = {500,100,50,10}; //동전 배열
		int coincount=0; //동전 갯수용 변수
		System.out.println("금액입력 :");
		inputmoney=scan.nextInt();
		for(int i=0; i<coin.length; i++) { 
			//처음에 입력된 값을 500 나눈 값을 갯수 변수에 저장하고 500을 나누고 남은 나머지값을 다시 입력한 값에 대입하여 나누고 나머지를 대입하고 그걸 다시 나누는걸 반복. 
			coincount=inputmoney/coin[i];
			System.out.println(coin[i]+"원짜리 "+coincount+"개");
			inputmoney=inputmoney%coin[i];
		}
		scan.close();
	}

}
