package test1;
public class 중첩for문 {

	public static void main(String[] args) {
		//중첩 반복문의 실행순서 분석하기
		for(int i=1; i<=2; i++) {
			for(int j=1; j<=3; j++) {
				System.out.print("i값 : " + i);
				System.out.println(", j값 : " + j);
			}
		}
		
		//구구단 출력해보기
		for(int i=1; i<=9; i++)
		{
			for(int j=1;j<=9;j++) {
				System.out.print(i + "X" + j + "=" + i*j + " " + "\t");
			    
			}
		    System.out.println();
		}	
	
		// 별을 5개부터 1개 출력
		for(int i=0; i<5; i++) {
			
			for(int j=5; j>i; j--) {
				
				System.out.print("*");
				
			}
			
			System.out.println("");
			
		}
		// 별을 1개부터 5개 출력
		for(int i=1; i<=5; i++) {
			
			{
				
			}
			
		for(int j=1; j<=i; j++) {
			
			System.out.print("*");
			
			}
						System.out.println(""); 
		}
	}
}
                                    
                                    
                                    
                                    
	                                                                                