package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main11 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 원하는 숫자를 입력하고 출력하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int inputnumber = Integer.parseInt(br.readLine()); // 문자열 입력을 정수형으로 변환하고 inputnumber에 대입
		bw.write(Integer.toString(45675642));
		bw.write(Integer.toString(inputnumber) + "\n");//inputnumber가 정수형이 되었으니 다시 문자형으로 변환한뒤 버퍼에 저장.
		bw.flush();
		System.out.println("입력을 한 숫자 : " + inputnumber);

	}

}
