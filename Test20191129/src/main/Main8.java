package main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main8 {

	public static void main(String[] args) {
		// Set을 활용하여 로또 생성하기.
		Set<Integer> set = new HashSet<Integer>(); //중복을 허용하지 않으면서 자료를 집합시킬때 사용하는 객체를 생성
		int num = 0;
		for(int i=1; set.size()<6; i++) { //중복을 허용하지 않으므로 중복된 숫자가 나올시 다시 반복, 그렇게하여 set에 추가된 num이 6개가 되면 반복을 멈춤.
		num = (int)(Math.random() * 45) +1;
		set.add(num);
		}
		
		ArrayList<Integer> ar = new ArrayList<Integer>(set); // set에 추가된 데이터 집합을 ar에 추가
		System.out.println(ar); //ar을 출력, set으로 출력해도 무방 어차피 같은 결과

	}


}
