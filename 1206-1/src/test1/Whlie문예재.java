package test1;
public class Whlie문예재 {

	public static void main(String[] args) {
		// 반복을 활용해서 1~10까지 누적합 계산결과 출력
		// for문 예재 참고.
		int i=1;
		int sum=0;
		while(i<=10)
		{
		System.out.print(i);
		sum+=i++;
		if(i<=10)
		{
			System.out.print("+");
		}
		
			
		}
		System.out.print("=" + sum);
		}
	}

