package test1;

public class 논리연산 {

	public static void main(String[] args) {
		/*  
		산술연산자 : +,-,*,/ %
	        대입연산자 : =,+=,-=,*=,/=,%=   ex) a += b = a+b                       
	        증감연산자 : ++(1 증가),--(1감소)  ex) a++,++a            
	        비교연산자 : >,<,>=,<=,==,!=  >> 비교연산 결과는 boolean 값으로 반환.
	        논리연산자 : &&(and),!(not),||(or) >> 논리 연산 결과도 boolean 값으로 반환.    
	        삼항연산자 : 조건식.                    													*/
		
		/* 논리연산자 사용(boolean 변수, 또는 비교연산식)
	1. && : A와 B 모두 참이어야 참,모두 거짓이어야 거짓.
	2. || : A건 B건 하나라도 참이면 참, A와 B 모두 거짓이어야 거짓.(하나만 거짓이면 참)
	3. ! : boolean 변수(true,false)의 결과 반전.
	*/
		boolean result=true && true;
		System.out.println(result);
		result=true && false;
		System.out.println(result);
		result=false && true;
		System.out.println(result);
		result=true || true;
		System.out.println(result);
		result=true || false;
		System.out.println(result);
		result=false || false;
		System.out.println(result);
		result=(4>3) && (5<2);
		System.out.println(result);
		result=(4>3) || (5<2);
		System.out.println(result);
		
		result= !result;
		System.out.println(result);// result의 결과는 true !를 붙여 결과 반전한것을 다시 result 대입. false 출력.
		
	}

}
