package test1;

public class 배열2차원 {

	public static void main(String[] args) {
		// (0,0) (45,2) 등 좌표 읽는것과 유사함. 
		int ar[] [] = new int [2] [3]; // 2행 3열의 배열
		System.out.println(ar.length); // 행의 갯수 출력이 됨
		System.out.println(ar[0].length); //1행의 열 갯수 출력이 됨
		System.out.println(ar[1].length); //2행의 열 갯수 출력이 됨
		for(int i=0; i<ar.length; i++) { //행의 크기만큼 반복
			System.out.println(" ");
			for(int j=0; j<ar[0].length; j++) //열의 크기만큼 반복
				System.out.print(ar[i][j]);
		}
	}

}
