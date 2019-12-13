package test1;

public class 형변환 {

	public static void main(String[] args) {
//		문자>숫자/숫자>문자 
//		1.자동 2.강제
//		1. 자동 : 크기가 작은 타입이>>큰 타입으로 갈때 int>>>double
//		2. 강제 : 반대의 경우 double>>int
		byte bytevalue = 127; 
		int intvalue = 129;
		double doublevalue=129;
		intvalue=bytevalue; // 1번의 경우
		System.out.println(intvalue);
		bytevalue=(byte)intvalue; // 2번의 경우
		System.out.println(bytevalue);
		bytevalue=(byte)doublevalue; // 2번의 경우
		System.out.println(bytevalue);
		
		// 단, 변수별로 사이즈가 다르니 사이즈를 넘는 범위의 값을 변환시 주의할것.
	}

}
