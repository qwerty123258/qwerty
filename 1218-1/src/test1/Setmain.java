package test1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Setmain {

	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("아야어여");
		set.add("아야어여");
		set.add("우유우이");
		set.add("우유이이이");
		set.add("유우이");
		set.add("우이");
		set.add("우우이");
		set.add("우유우");
		System.out.println(set); //중복된것 저장 안됨. 리스트와 차이는 인덱스번호가 없어 순서가 없음.
		
		Iterator<String> iterator = set.iterator(); 
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println(" "); 
		set.remove("아야어여"); //데이터 삭제하고 다시 반복이용해서 출력할시에는
		iterator=set.iterator(); // 현재 set 상태를 대입을 해주어야 값이 제대로 나옴.
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
