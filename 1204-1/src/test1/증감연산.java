package test1;

public class 증감연산 {

	public static void main(String[] args) {
		/*  
		산술연산자 : +,-,*,/ %
	        대입연산자 : =,+=,-=,*=,/=,%=   ex) a += b = a+b                       
	        증감연산자 : ++(1 증가),--(1감소)  ex) a++,++a            
	        비교연산자 : >,<,>=,<=,==,!=  >> 비교연산 결과는 boolean 값으로 반환.
	        논리연산자 : &&(and),!(not),||(or) >> 논리 연산 결과도 boolean 값으로 반환.    
	        삼항연산자 : 조건식.                    													*/
		
		/* 증감연산자 사용(++,--)
		 * ++변수 : 증가시킨뒤 연산에 사용
		 * 변수++ : 연산에 사용한뒤 값을 증가시킴.
		 */

		int a=10;
		int b=12;
		int result = a++;
		System.out.println("a++ 수행전 : "+ result);
		result = a++;
		System.out.println("a++ 수행후 : "+ result);
		result = a++;
		System.out.println("a++ 2번째 수행후 : "+ result); //연산에 사용한뒤에 값이 증가.
		
		result = ++b;
		System.out.println("++b 수행전 : "+ result);
		result = ++b;
		System.out.println("++b 수행후 : "+ result); // 연산에 사용하기 전에 값이 증가되어 출력
		
		a= b++;
		System.out.println("a 값 : "+ a); // a의 값은 12, b의 값은 14 
		System.out.println("b 값 : "+ b); // b의값이 연산에 사용된후 값이 증가하고 나서 출력되어 b의 값은 15
		a= ++b;
		System.out.println("a 값 : "+ a); // a의 값은 14, b의 값은 15
		System.out.println("b 값 : "+ b); // 연산에 사용되기전에 증가하고 출력이 되므로 b의 값은 16 이를 a에 대입하므로 a 역시 16
		
		int num1 = 14;
		int num2 = 10;
		int num3 = 14;
		
		num3 = ++num1 + num2++; 
		System.out.println("num1 값 : "+ num1); // 연산이 되기전 14에서 1 증가해서 15
		System.out.println("num2 값 : "+ num2); // 연산이 된 후에 1이 증가해서 11
		System.out.println("num3 값 : "+ num3); // 15+10 = 25
		
		num3 = ++num1 + ++num2; 
		System.out.println("num1 값 : "+ num1); // 연산이 되기전 15에서 1 증가해서 16
		System.out.println("num2 값 : "+ num2); // 연산이 되기전 11에서 1 증가해서 12
		System.out.println("num3 값 : "+ num3); // 16+12=28
		
		num3 = num1++ + num2++; 
		System.out.println("num1 값 : "+ num1); // 연산이 된 후에 16에서 1 증가해서 17
		System.out.println("num2 값 : "+ num2); // 연산이 된 후에 12에서 1 증가해서 13
		System.out.println("num3 값 : "+ num3); // 16+12=28
		
		num3 = num1++ + ++num2; 
		System.out.println("num1 값 : "+ num1);  // 연산이 된 후 17에서 1증가해서 18
		System.out.println("num2 값 : "+ num2);  // 연산이 되기전 13에서 1증가해서 14
		System.out.println("num3 값 : "+ num3);  // 17+14 = 31
		
		
	}

}
