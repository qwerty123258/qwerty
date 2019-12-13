package test1;

import java.util.Scanner;

public class 배열2차원응용 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("몇 년제인지 입력하세요.");
		int grade= scan.nextInt(); //몇년제인지
		System.out.println("몇 학기제인지 입력하세요.");
		int quarter= scan.nextInt(); //몇학기제인지
		double ar[] [] = new double[grade][quarter];
		double totalscore=0;
		for(int i=0; i<ar.length; i++) {
			System.out.println(" ");
			for(int j=0; j<ar[i].length; j++) {
				System.out.println((i+1)+"학년" + (j+1) +"학기에 받은 학점을 입력하세요.");
				double score = scan.nextDouble(); //각 학기에 점수를 몇 받았는지
				ar[i][j]=score; //학기별 점수를 2차원 배열에 다 넣어줌.
				totalscore+=score;
			}
		}
		for(int i=0; i<ar.length; i++) { //행의 크기만큼 반복
			System.out.println(" ");
			for(int j=0; j<ar[i].length; j++) //열의 크기만큼 반복
				System.out.print(" "+(i+1)+ "학년"  + (j+1)+ "학기 :" + ar[i][j] + "\n");
		}
		
		System.out.println("총 평점은"+totalscore+"입니다. 평균은 "+totalscore/(ar.length*ar[0].length) +"입니다.");
		scan.close();

	}

}
