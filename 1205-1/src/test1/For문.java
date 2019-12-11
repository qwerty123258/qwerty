package test1;
public class For문 {
	public static void main(String[] args) {
//		1)for ( 기본 값 - i ex)int i=0; 반복종료 조건 -ex)i<10; 반복변수 증가조건-증감연산자 ex)i++ )
//		{
//		}
		//1~3미만까지 정수로 반복출력
		for(int i=1;i<3;i++)
		{System.out.println("반복변수 값 : " + i);
		}
		System.out.println("반복문 종료");
		
		//3~9까지 반복 출력
		for(int i=3;i<=9;i++)
		{System.out.println("반복변수 값 : " + i);
		}
		System.out.println("반복문 종료");
		
		//i를 위에서 미리 선언
		
		int i=0;
		for(i=1;i<=3;i++) //이미 선언된 변수에 반복 사용시 값 바뀔 수 있음.
		{System.out.println("반복변수 값 : " + i);
		}
		System.out.println("반복문 종료후 i 값 :" + i);
		System.out.println("반복문 종료");
	}
}
