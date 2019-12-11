package test3;
import java.util.*;
public class 중첩IF문 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		String b = "학점";
		
		if(a>100) System.out.println("입력범위를 초과하였습니다.");
		else if (a <= 100) 
		{ 
			if(a>=95) b="A+"; 
		else if(a>=90) b="A"; 
		else if(a>=85) b="B+"; 
		else if(a>=80) b="B"; 
		else if(a>=75) b="C+"; 
		else if(a>=70) b="C"; 
		else if(a>=65) b="D+"; 
		else if(a>=60) b="D"; 
		else if(a<60) b="F"; System.out.println("당신의 학점은 "+ b + "입니다.");
		}
}
}
