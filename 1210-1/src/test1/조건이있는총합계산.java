package test1;
import java.util.*;
public class 조건이있는총합계산 {

	public static void main(String[] args) {
		// 1부터 입력한 숫자까지의 총합계산(단, 2와 3의배수는 제외하고)
		Scanner scan = new Scanner(System.in);
        int input = scan.nextInt(); 
        int sum = 0;
        for(int i=1; i<=input; i++) {
            if(i%3!=0 && i%2!=0)
            	sum+=i;
        }
    	System.out.println(sum);
    }
	

}

