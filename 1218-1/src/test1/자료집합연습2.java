package test1;
import java.util.*;
public class 자료집합연습2 {

	public static void main(String[] args) {
		String max=null;//최대값 출력용 변수
		String min=null;//최소값 출력용
		int max1=Integer.MIN_VALUE; //최대길이 비교용 변수
		int min1=Integer.MAX_VALUE; //최소길이 비교용
		List<String> wordList = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		for(int i=0; i<=4; i++) {
			System.out.println("단어를 입력하세요.");
			String input=scan.next();
			wordList.add(i,input);
		}
		for(int i=0; i<wordList.size(); i++) {
				if(wordList.get(i).length()>max1) {
					max1=wordList.get(i).length();
					max=wordList.get(i);
			}
				if(wordList.get(i).length()<min1) {
					min1=wordList.get(i).length();
					min=wordList.get(i);
			}
		}
		System.out.println(wordList);
		System.out.println("제일 짧은 단어:" +min);
		System.out.println("제일 긴 단어:" +max);

	}
	

}
