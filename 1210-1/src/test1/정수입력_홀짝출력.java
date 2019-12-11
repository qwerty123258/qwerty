package test1;
import java.util.*;
public class 정수입력_홀짝출력 {

	public static void main(String[] args) {
		// 정수를 입력하여 홀수/짝수를  판단하여 출력하게 하고 0이 입력되면 반복이 종료되게하기.
		Scanner scan = new Scanner(System.in);;
		boolean run = true;
		while(run) {
			int input = scan.nextInt();
			if(input%2!=0)
				System.out.println("홀수");
			else if(input==0) run=false;
			else if(input%2==0) 				
				System.out.println("짝수");
		}
	}

	}
