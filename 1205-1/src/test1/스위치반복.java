package test1;
import java.util.*;
public class 스위치반복 {

	public static void main(String[] args) {
//		간단한 사전
//		영단어>>한글의미
//		사전에 단어는 4개
		for(;;) {
		Scanner scan = new Scanner(System.in);
		System.out.print("단어를 입력하세요 : ");
		String Word= scan.next();
		switch(Word)
		{case "car": System.out.println("car : 자동차,차"); 
		break;
		case "cat": System.out.println("cat : 고양이"); 
		break;
		case "number": System.out.println("number : 숫자,번호"); 
		break;
		case "dog": System.out.println("dog : 개,강아지"); 
		break;
		default:System.out.println("사전에 없는 단어입니다.");
		break;
		}
		}
	}
}
