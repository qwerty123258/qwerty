package test1;

public class 삼항연산 {

	public static void main(String[] args) {
		/*  
		산술연산자 : +,-,*,/ %
	        대입연산자 : =,+=,-=,*=,/=,%=   ex) a += b = a+b                       
	        증감연산자 : ++(1 증가),--(1감소)  ex) a++,++a            
	        비교연산자 : >,<,>=,<=,==,!=  >> 비교연산 결과는 boolean 값으로 반환.
	        논리연산자 : &&(and),!(not),||(or) >> 논리 연산 결과도 boolean 값으로 반환.    
	        삼항연산자 : 조건식.                    													*/
		
		/*	삼항연산자 사용
		 * 	= (조건식) ? true일때 : false일때;
		 */
		
int score = 90;
String grade=(score>=90) ? "합격" : "불합격";
System.out.println("grade 변수 값 : " + grade);

	}

}
