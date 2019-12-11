package test1;

public class 비교연산 {

	public static void main(String[] args) {
		/*  
		산술연산자 : +,-,*,/ %
	        대입연산자 : =,+=,-=,*=,/=,%=   ex) a += b = a+b                       
	        증감연산자 : ++(1 증가),--(1감소)  ex) a++,++a            
	        비교연산자 : >,<,>=,<=,==,!=  >> 비교연산 결과는 boolean 값으로 반환.
	        논리연산자 : &&(and),!(not),||(or) >> 논리 연산 결과도 boolean 값으로 반환.    
	        삼항연산자 : 조건식.                    													*/
		
		//비교 연산자 사용
int a =1, b =4; 
boolean result1= a < b;
System.out.println(result1); 
result1= a > b;
System.out.println(result1);
result1= a >= b;
System.out.println(result1);
result1= a <= b;
System.out.println(result1);
result1= a == b; // = : 우변을 좌변에 대입, == : 비교 연산자
System.out.println(result1);
result1= a != b;
System.out.println(result1);

a=1;
double c=1.0;
result1=a==c;
System.out.println(a + " " + c + " " + result1); // 사이즈가 작은 것이 큰 곳에 맞춰지는 경우.

String d="자바";
String e="자바";
result1 = d==e;

System.out.println(result1); // 문자열도 비교 가능하지만 구성에 따라서 보통은 안 됨.

// cf. 문자열 비교
result1 = d.equals(e);
System.out.println(result1); 
	}

}
