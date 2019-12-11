package test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Test1 {

	public static void main(String args[]) throws NumberFormatException, IOException {
		// 스캐너 대신에 다른 입출력 수단을 사용하여 몇번의 계산을 할지 입력하고 특정 수를 입력하여 수들의 합을 계산하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		//new InputStreamReader(System.in)로부터 값을 읽는  BufferedReader 객체 br 생성
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		//new OutputStreamWriter(System.out)로부터 값을 읽는 BufferedWriter 객체 bw 생성
		int input = Integer.parseInt(br.readLine()); //버퍼에 저장되어 있는 값은 문자열이므로 정수형으로 변환하여 input이라는 변수를 선언하고 대입
		//readLine() : 한줄씩 읽는다는 의미.
		if(0<input && input<=1000000) { // if 조건에 맞아야 반복 실행
			for(int i=1; i<=input; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(),","); // 사용자가 정한 구분자(공백이나 ,)로 데이터를 구분하는 객체 생성.
				int num1 = Integer.parseInt(st.nextToken()); //토큰 단위로 입력
				int num2 = Integer.parseInt(st.nextToken());
				if(1<=num1 && num2<=1000 && 1<=num1 && num2<=1000)
					bw.write(Integer.toString(num1+num2) + "\n"); //if 조건에 맞는 내용만 버퍼에 저장, 줄바꿈이 스스로 되지 않기때문에 지정
			}
			bw.flush(); // 버퍼에 저장된 값을 한번에 출력
		}
	}

}
