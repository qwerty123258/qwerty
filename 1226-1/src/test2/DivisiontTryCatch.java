package test2;

import java.util.Scanner;

public class DivisiontTryCatch {

	public static void main(String[] args) {
		try {
			division();
		}
		catch(Exception e) {
			System.out.println("0으로 못 나눔");
		}

	}

	public static void division() {
		 Scanner scan = new Scanner(System.in);
		 int num1=scan.nextInt();
		 int num2=scan.nextInt();
		 System.out.println(num1/num2);
	}

}
