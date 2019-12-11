package main;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
public class Main10 {

	public static void main(String[] args) throws IOException {
		//스캐너 대신에 다른 입출력수단 사용해보기.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		bw.write(input);
		bw.write("아자차카타파하" + "\n");
		System.out.println("입력을 한 문자 : " + input);
		bw.flush();
			}


	}

