package test1;

import java.util.Scanner;

public class 영단어맞추기게임 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String ar[][] = {{"car","cat","dog","tiger","lion"},{"차","고양이","개","호랑이","사자"}};
		double rightanswercount=0;
		double totalcount=0;
		double avg=0;
		for(int i=0; i<(ar.length-1); i++) {
			for(int j=0; j<ar[i].length; j++) {
				System.out.println(ar[i][j] + "의 뜻을 입력하세요.");
				String inputword = scan.next(); //입력해서 넣을 단어
				if(inputword.equals(ar[i+1][j])) {
					rightanswercount++;
				}
				if(!inputword.equals(ar[i+1][j])) {
					System.out.println("틀렸습니다. 공부좀 하시죠.");
					System.out.println("정답은 "+ ar[i+1][j] + "입니다.");
				}
				totalcount++;
				avg=(rightanswercount/totalcount)*100;
			} 
		}
		System.out.println("맞춘 개수 : "+(int)rightanswercount);
		System.out.println("정답률 :" + avg + "%");
		scan.close();
	}

}
