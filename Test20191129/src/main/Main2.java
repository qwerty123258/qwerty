package main;

public class Main2 {

	public static void main(String[] args) {
		// *를 기존과는 다르게 */**/***/****/***** 의 형태로 출력하기.
		for(int i=1; i<=5; i++) { //외부 for문에서 i가 5가 될때까지 5번 반복한다는 의미.
			for(int j=5; j>=i; j--) {
				// j가 5부터 시작해서 i랑 같아질때까지 반복한다는 의미이고 반복할때마다 *을 줄바꿈 없이 출력.
				// j가 5로 시작하고 i가 1이면 5,4,3,2,1 5번 반복해서 *은 5개가 출력이 됨.
				// 공백이 출력이 되었으면 i는 1이 증가한 상태고 j가 2가 될때가지 4번 반복한다. *은 4개가 출력된다.
				System.out.print("*");
		}
			System.out.println(""); // 내부 for문의  반복이 끝나면 줄바꿈의 의미로 공백을 출력함.
			
		}
	}

}
