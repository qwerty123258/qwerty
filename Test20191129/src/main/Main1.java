package main;

public class Main1 {

	public static void main(String[] args) {
		// for 중첩을 활용하여 */**/***/****/***** 형태로 출력하기.
		for(int i=1; i<=5;i++) // 외부 for문에서 i가 5가 될 때까지 5번 반복한다는의미.
		{
			for(int j=1; j<=i; j++) { 
				// j가 i보다 숫자가 작을때부터 같아질때까지 반복한다는 의미인데 반복할때마다 *을 줄바꿈 없이 출력.
				// 만약에 i가 1이면 j가 1이므로 내부 for문은 1번 실행되서 * 은 1개만 출력
				// 공백을 출력했으면 다시 외부 for문이 시작되고 내부 for문이 시작됨.
				// 공백을 출력한 뒤이므로 i는 1이 증가하였고 j역시 1이 증가됨. 따라서 그 전에 * 보다 1개 더 많은 *이 출력됨.
				System.out.print("*");
				}
				
			System.out.println("");
			// 내부 for문의 반복이 끝나면 줄바꿈의 의미로 공백을 출력함.
		}
		}

	}
