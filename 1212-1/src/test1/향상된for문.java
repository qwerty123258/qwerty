package test1;

public class 향상된for문 {

	public static void main(String[] args) {
		int[] num= {3,4,5,3,1};
		
		for(int i=0; i<num.length; i++) { //기존 for문
			System.out.print(num[i]);
		}
		System.out.println("");
		
		for(int a:num) { //향상된 for문
			System.out.print(a);
		}
		
		String[] charcter= {"car " + "cat " + "for " + "where " + "win"}; // 향상된 for문 문자열 배열
		
		System.out.println("");
		
		for(String a:charcter) { //향상된 for문
			System.out.print(a);
		}

	}

}
