package test1;
import java.util.Scanner;
public class 응용해보기 {

	public static void main(String[] args) {
		//그 전에 했던 간단한 사전 만들기에서
		// 한번에 검색을 여러번 사용할수 있게끔 반복을 시키고
		// 'exit'를 입력하면 종료되게끔 하기.
		Scanner scan = new Scanner(System.in);
		boolean start=true;
		while(start) {
		System.out.print("단어를 입력하세요 : ");
		String Word= scan.next();
		String close="'exit'";
		switch(Word)
		{case "car": System.out.println("car : 자동차,차"); 
		break;
		case "cat": System.out.println("cat : 고양이"); 
		break;
		case "number": System.out.println("number : 숫자,번호"); 
		break;
		case "dog": System.out.println("dog : 개,강아지"); 
		break;
		case "'exit'": System.out.println("종료"); 
		break;
		default:System.out.println("사전에 없는 단어입니다.");
		break;
		}
		if(Word.equals(close)) start=false;
		}
	}
}
