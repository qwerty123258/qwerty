package test1;
public class 스위치케이스 {

	public static void main(String[] args) {
		int num1=0;
		switch(num1)
		{case 0: System.out.println("값 : 0"); //num1 이 0인 경우
		break;
		case 1: System.out.println("값 : 1"); //num1 이 1인 경우
		break;
		case 2: System.out.println("값 : 2"); //num1 이 2인 경우
		break;
		default:System.out.println("값이 없음"); //num1 이 값이 없는 경우
		break;
		}
		System.out.println("Switch~case문 종료");
		
		}
	}
