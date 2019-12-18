package test1;
import java.util.*;
public class 자료집합연습2 {

	public static void main(String[] args) {
		String max=null;//최대값 출력용 변수
		List<String> wordList = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		for(int i=0; i<=4; i++) {
			System.out.println("단어를 입력하세요.");
			String input=scan.next();
			wordList.add(i,input);
		}
		for(int i=0; i<wordList.size(); i++) {
				if(wordList.get(i).length()>wordList.get(i).length()) {
					max=wordList.get(i);
			}
		}
		System.out.println(max);

	}
	

}
