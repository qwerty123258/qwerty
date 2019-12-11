package test1;

import java.util.Scanner;

public class 업다운 {

	public static void main(String[] args) {
		// 숫자 맞추기(숫자는 랜덤으로)
		Scanner scan = new Scanner(System.in);
		int randomnum = (int) (Math.random()*1000)+1;
		System.out.println("1~1000까지의 숫자를 맞춰보세요.");
		System.out.println("10회안에 맞추기!! 초과시 종료.");
		int count=0;
		for(;;) {
			if(count==15) {
				System.out.println("끝! ㅅㄱㅇ"); 
			break;
			
			}
			int T = scan.nextInt();
			
			if(T==randomnum) {
				
			System.out.println("정답!!!!!" +"\n" + "시도 횟수 :" + count);
			break;
			
			}
			else if(T>randomnum) {
				
				System.out.println("더 작은 수를 입력하세요.");
			
			}
			else if(T<randomnum) {
				
				System.out.println("더 큰 수를 입력하세요.");
			}
				count++;
			}
		}

	}


