package test3;
import java.util.*;
public class 시계 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("초를 입력하세요. : ");
		int a=scan.nextInt(); // a에 입력하는 정수로 대입
		System.out.println(a/3600 + "시간" + a%3600/60+ "분" + a%3600%60+ "초");
		// 3600s=1h, 60s=1m, 60m=1h
		}

	}


