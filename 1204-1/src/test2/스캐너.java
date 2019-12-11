package test2;

import java.util.Scanner; // 스캐너라는 클래스 호출

public class 스캐너 {

	public static void main(String[] args) {
		   // 스캐너를 사용하기 위해 객체 선언
				Scanner scan = new Scanner(System.in); 
				// 키보드 입력이나 기타 입력(System.in)을 받는 새(new) 스캐너 클래스를(scan)이라고 생성.
				String name, address;
				int age;
				System.out.print("이름을 입력하세요 : "); // 서순을 신경쓰기.
				name= scan.next(); // 스캐너 클래스의 next() 메소드 실행 결과를 name에 대입
				System.out.print("나이를 입력하세요 : ");
				age = scan.nextInt(); // 스캐너 클래스의 nextInt() 메소드 실행 결과를 age에 대입
				System.out.print("주소를 입력하세요 : ");
				address = scan.next();
				
				System.out.println("입력한 이름 : " + name);
				System.out.println("입력한 나이 : " + age);
				System.out.println("입력한 주소 : " + address);


	}

}
