package test1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Mapmain {

	public static void main(String[] args) {
		Map<Integer,String> map = new HashMap<Integer,String>();
		Map<String,String> map1 = new HashMap<String,String>();
		map.put(0, "첫번째 값");
		map.put(1, "두번째 값");
		map.put(2, "세번째 값"); //list와 set과는 다른 put 메서드 이용.
		map1.put("가", "첫번째 값");
		map1.put("나", "두번째 값");
		map1.put("다", "세번째 값"); //list와 set과는 다른 put 메서드 이용.
		System.out.println(map);
		System.out.println(map.get(0));
		System.out.println(" "); 
		System.out.println(map1);
		System.out.println(map1.get("가"));
		System.out.println(" "); 
		map.put(0, "네번째 값");
		System.out.println(map);
		System.out.println(map.get(0)); //중복되는 key를 사용하는 경우 나중에 사용한 key의 value로 바뀜.
		System.out.println(" "); 
		Set<Integer> keyvalues = map.keySet(); //map 객체에서 key값을 set에 keyvalues란 형태로 저장.
		Iterator<Integer> iterator =keyvalues.iterator(); //저장된 값을 반복자에 대입후
		while(iterator.hasNext()) { //반복을 하고
			int key=iterator.next(); //key는 숫자니까 int로 했지만 string으로 했다면 변경해도 되고. 무튼 얻어온 데이터들을 key에 대입하고
			String value=map.get(key); //key에 들어있는 데이터들을 value에 대입
			System.out.println(key+" : "+value);
		}
		
		Set<String> keyvalues1 = map1.keySet(); //key값을 맵에서 반환하고 set에 keyvalues란 형태로 저장.
		Iterator<String> iterator1 =keyvalues1.iterator(); //저장된 값을 반복자에 대입후
		while(iterator1.hasNext()) { //반복을 하고
			String key=iterator1.next(); //무튼 얻어온 데이터들을 key에 대입하고
			String value=map1.get(key); //key에 들어있는 데이터들을 value에 대입
			System.out.println(key+" : "+value);
		}
		
	}

}
