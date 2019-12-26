package test2;

public class StringMethod {

	public static void main(String[] args) {
		String str = "자바는 재밌다"; //변수처럼 생각했지만 java.lang.String이라는 클래스.
		//String 클래스 타입의 str이라는 변수에 문자열을 대입했을뿐.
		System.out.println(str.length()); //공백도 문자.
		String newstr = str.replace("자바", "JAVA");
		System.out.println(newstr); //일부 문자열만 교체.
		String str2 = str.substring(0, 1); //시작부분 부터 끝까지만 출력, 이 부분만 자르는 것이 아님.
		System.out.println(str2);
		String str3=str.substring(2);
		System.out.println(str3); // 해당하는 인덱스까지 다 짤림.
		String str4 = "abcdEFgH";
		String str4lower = str4.toLowerCase();
		System.out.println(str4lower); //모두 소문자
		String str4upper = str4.toUpperCase();
		System.out.println(str4upper); //모두 대문자
		String str5 = "             ABc";
		System.out.println(str5);
		String str5space = str5.trim();
		System.out.println(str5space); //공백 제거
		String str6 = "100";
		int num1=Integer.parseInt(str6);
		System.out.println(num1+num1); //문자열로 되어있는 숫자를 형변환.
		String str7 = "a100";
		try {
		int num2=Integer.parseInt(str7);
		}
		catch(Exception e) {
			System.out.println("a를 변환할수 없어서 에러 발생");
			e.printStackTrace();
		}

	}

}
