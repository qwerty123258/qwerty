package test1;

public class 대입연산 {

	public static void main(String[] args) {
		/*  
		산술연산자 : +,-,*,/ %
	        대입연산자 : =,+=,-=,*=,/=,%=   ex) a += b = a+b                       
	        증감연산자 : ++(1 증가),--(1감소)  ex) a++,++a            
	        비교연산자 : >,<,>=,<=,==,!=  >> 비교연산 결과는 boolean 값으로 반환.
	        논리연산자 : &&(and),!(not),||(or) >> 논리 연산 결과도 boolean 값으로 반환.    
	        삼항연산자 : 조건식.                    													*/
		
		// 대입연산자 사용
		int a=10;
		int b=4;
		int result = a += b;
		System.out.println(result); 
		result = a -= b;
		System.out.println(result); 
		result = a *= b;
		System.out.println(result); 
		result = a /= b;
		System.out.println(result); 
		result = a %= b;
		System.out.println(result); // a의 result가 줄 마다 바뀜.(헷갈릴뻔) 

	}

}
