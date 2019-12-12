package test1;

import java.util.Scanner;

public class 향상된for문연습 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("숫자입력 :");
		int choice = scan.nextInt();
		int num[] = {1,2,5,4,8,6,9,7,3,10};
		int count=0;
		for(int a:num) {
			count++;
			if(a==choice) { //내가 입력한 수가 
				System.out.println(choice + "는  " + count +"번째에 있습니다.");
			}
		}
	}

}
