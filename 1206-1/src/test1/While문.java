package test1;

public class While문 {

	public static void main(String[] args) {
//		while(조건식,bool 타입) {
//			
//			반복실행내용
//		}
		int i=1;
		boolean run=true;
	   while(run)
	   {
		   System.out.println(i);
	   i++;
	   if(i==100) run = false;
	   }
	   System.out.println("While 종료");
	   System.out.println(i);
	}

}
