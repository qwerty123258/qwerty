package test1;

public class 배열2차원데이터넣기 {

	public static void main(String[] args) {
		int ar[] [] = {{3,4,6,6} , {5,4,4,6},{5,8,4,7}};
		System.out.println(ar.length); // 행의 갯수 출력이 됨
		System.out.println(ar[0].length); //1행의 열 갯수 출력이 됨
		System.out.println(ar[1].length); //2행의 열 갯수 출력이 됨
		for(int i=0; i<ar.length; i++) { //행의 크기만큼 반복
			System.out.println(" ");
			for(int j=0; j<ar[i].length; j++) //열의 크기만큼 반복
				System.out.print(" "+(i+1)+ "행"  + (j+1)+ "열 :" + ar[i][j]);
		}
	}

}
