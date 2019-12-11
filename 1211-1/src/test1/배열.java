package test1;
public class 배열 {

	public static void main(String[] args) {
		// 데이터가 여러개 있을때 하나의 변수에 저장함.
		// 배열의 첫 시작위치는 0. << 헷갈리지 않기. 사이즈로 크기를 정할수 있음      
		int ar[] = {1,2,3}; // 정수형 배열
		String[] ar2 ={"가","나","다"}; //문자열 배열 [] 위치는 타입 옆이나 변수 옆 상관 x
		double sum=0;
		double avg=0;
		
		for(int i=0; i<3; i++) {
		System.out.print(ar[i]);
		}
		System.out.println("");
		
		for(int i=0; i<ar2.length; i++) {//배열의 사이즈보다 작을때까지 반복
			System.out.print(ar2[i]);
		}
		double ar3[] = {456,5645,8714,4154};
		System.out.println("");
		for(int i=0; i<ar3.length; i++) {
			sum+=ar3[i];
			System.out.println("배열에 저장된 값:"+ ar3[i]);
			avg=sum/ar3.length;
		}
		System.out.println("총합 :"+ sum);
		System.out.println("평균 :" +avg);
		
		System.out.println("");
		
		int[] ar4 = new int[3];
		System.out.println(ar4[0]);
		System.out.println(ar4[1]);
		System.out.println(ar4[2]);
		ar4[1] = 3;
		ar4[2]= 56;
		System.out.println(ar4[0]);
		System.out.println(ar4[1]);
		System.out.println(ar4[2]);
		
		int ar5[] = null;
		ar5 = new int[3]; //이렇게 null로 미리 선언만 해놓고 쓰는 경우도 있음. 전역변수 사용시
		
		
	
		

	}

}
