package test1;
import java.util.*;
public class 자료집합연습 {

	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
		Scanner scan = new Scanner(System.in);
		double count=0;// 맞춘 갯수 변수
		double avg=0; // 정답률 변수
		String input=null;//입력용 변수
		map.put("Lion", "사자");
		map.put("Tiger", "호랑이");
		map.put("Dog", "개");
		map.put("Watch", "시계");
		map.put("Chair", "의자");
		map.put("Desk", "책상");
		map.put("Animal", "동물");
		map.put("Monkey", "원숭이");
		map.put("Doctor", "의사");
		Set<String> keyvalues = map.keySet(); 
		Iterator<String> iterator =keyvalues.iterator(); 
		while(iterator.hasNext()) { 
			String key=iterator.next(); 
			String value=map.get(key); 
			System.out.println(key + "의 뜻을 입력하세요.");
			input=scan.next();
			switch(input)
			{
			case "사자":     if(input.equals(value)) {count++;}
			break;
			case "호랑이":   if(input.equals(value)) {count++;}
			break;
			case "개":  if(input.equals(value)) {count++;}
			break;
			case "시계":  if(input.equals(value)) {count++;}
			break;
			case "의자":  if(input.equals(value)) {count++;}
			break;
			case "동물":  if(input.equals(value)) {count++;}
			break;
			case "책상":  if(input.equals(value)) {count++;}
			break;
			case "원숭이":  if(input.equals(value)) {count++;}
			break;
			case "의사":  if(input.equals(value)) {count++;}
			break;
			default:  System.out.println("틀렸습니다."); System.out.println("정답은 " + value +" 입니다."+"\n");
			}
			}
		avg=(double)count/map.size();
		System.out.println("맞춘 단어의 수 :" + (int)count+"개");
		System.out.println("정답률 " + avg*100 +"%");
		}
		

	}
