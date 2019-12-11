package test1;
import java.util.*;
public class for문예재 {
	public static void main(String[] args) {
		int a=0;
    for(int i=1; i<=10; i++)
    {a+=i; //a <<< a=a+i   a=0+1 >>> a=1+2 >> a=3+3 >> a= 6+4 >> a= 10 +5 >> a= 15 + 6
    // >> a=21+7 >> a= 28 +8 a= 36 +9 a= 45 + 10
    }
    System.out.println("1~10까지의 누적합 :" + a);
    
	int b=0;
    for(int i=1; i<11; i++)
    {b+=i; //a <<< a=a+i   a=0+1 >>> a=1+2 >> a=3+3 >> a= 6+4 >> a= 10 +5 >> a= 15 + 6
    // >> a=21+7 >> a= 28 +8 a= 36 +9 a= 45 + 10
    if(i<10 && b<55) {System.out.print(i); System.out.print("+");}
    else System.out.println(i + "="+ a);
    }
    
    // 시작값,끝값을 입력 받아서 시작값부터 끝값까지의 총합 계산
    Scanner scan = new Scanner(System.in);
    System.out.println("시작 값을 입력하세요 :");
    int c = scan.nextInt();
    System.out.println("끝 값을 입력하세요 :");
    int d = scan.nextInt();
	int e=0;
    for(int i=c; i<=d; i++)
    { e+=i; if(i<d) {System.out.print(i); System.out.print("+");} else System.out.println(i + "="+ e); }
    System.out.println(c+"부터"+ d+ "까지의 합은"+e+"입니다.");
	}
	
	
}
