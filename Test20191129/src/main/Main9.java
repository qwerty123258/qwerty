package main;
import java.util.*;
public class Main9 {

	public static void main(String[] args) {
		// Set과 ArrayList 차이 확인하기.
		ArrayList<Integer> ar = new ArrayList<Integer>();
		Set <Integer> set = new HashSet();
		ar.add(10);
		ar.add(20);
		ar.add(30);
		ar.add(40);
		ar.add(50);
		ar.add(50);
		ar.add(50);
		set.add(10);
		set.add(20);
		set.add(30);
		set.add(40);
		set.add(50);
		set.add(50);
		set.add(50);
		System.out.println(ar); // ArrayList에 추가된 집합 출력
		System.out.println(set); //Set에 추가된 집합 출력
	
		// 차이점
//		1. ArrayList는 데이터를 추가한 순서대로 출력된다.
//		2. Set은 순서와 상관없이 출력된다.
//		3. ArrayList는 데이터가 중복되어도 추가가되고 출력도 된다.
//		4. Set는 데이터가 중복이되면 추가가 되지않고 출력도 되지 않는다.
		

	}

}
