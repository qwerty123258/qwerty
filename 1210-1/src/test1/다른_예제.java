package test1;
import java.util.*;
public class 다른_예제 {

	public static void main(String[] args) {
		// 숫자를 여러번 입력하여 입력한 숫자의 갯수와 평균을 구하기.
		Scanner scan = new Scanner(System.in);
		System.out.print("정수를 입력하고 마지막에 0을 입력하세요. :");
		boolean run = true;
		int count=0;
		double sum = 0;
		double avg=0;
		while(run) {
			int input = scan.nextInt();
			if(input==0) run=false;
			count++;
			sum+=input;
			avg=sum/count;
		
		
		}
		System.out.println("입력한 수는 "+ count + "개");
		System.out.println("평균은"+ avg + "입니다.");

		
	}

}
