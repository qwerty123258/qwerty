package test1;

import java.util.ArrayList;
import java.util.List;

public class ArrayListmain {

	public static void main(String[] args) {
//		Collection Frame work
//		1. List : 중복저장이 되고 순서가 유지 됨
//		2. Set : 중복저장이 되지 않고 순서도 없다.
//		3. Map : key와 value의 쌍으로 저장이 되고 key의 값은 중복이 안된다.
		List<String> list = new ArrayList<String>();
//		숫자라면 Integer	
//		<> : generic
//		타입변환을 하지 않고도 데이터를 사용할수 있게 해준다. list는 기본 저장 타입이 object라서 활용하기 위해선 형변환해야함..그래서 귀찮아서 씀.
		list.add("첫번째");
		list.add("첫번째"); //중복인것 저장 가능.
		list.add("세번째");
		list.add("네번째"); //사이즈는 미리 정하지 않아도 추가는 계속 가능
		list.add(0,"다섯번째"); //인덱스번호를 미리 지정하고 add를 하면 기존에 추가한 데이터들이 밀려남.
		list.add(1,"여섯번째");
		System.out.println(list); //리스트 전체 출력
		System.out.println(list.get(3)); //리스트 특정 인덱스의 값을 볼때
		System.out.println(list.size()); // 리스트 크기볼때
		
		list.remove(0); //특정 인덱스의 데이터 지울때 지워지면 뒤에 데이터들이 앞으로 땡겨옴.
		list.remove("첫번째"); // 입력한 데이터와 같은 모든 인덱스가 지워지는게 아니라 가장 최근에 데이터만 지워짐.
		list.clear(); // 전부 삭제
		
		for(int i=0; i<list.size(); i++) { //배열처럼 노가다 ㄴㄴ 반복 ㅇㅇ
			System.out.println(list.get(i));
		}
	}

}
