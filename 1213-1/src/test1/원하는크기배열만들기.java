package test1;

import java.util.Scanner;

public class 원하는크기배열만들기 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("만들 배열의 크기를 입력하세요.");
		int num = scan.nextInt();
		int ar[] = new int[num];
		System.out.println("입력한 크기만큼의 데이터를 입력하세요.");
		for(int i=0; i<ar.length; i++) {
			num = scan.nextInt();
			ar[i] = num;
		}
		for(int j=0; j<ar.length; j++) {
			System.out.println(ar[j]);
		}
		System.out.println("사이즈 :" + ar.length);
		scan.close();
		
		
	}

}
