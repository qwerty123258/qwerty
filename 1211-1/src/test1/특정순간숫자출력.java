package test1;
public class 특정순간숫자출력 {

	public static void main(String[] args) {
		// 1-2+3~~~~~~~~~+99-100 >=100 인순간의 숫자 출력
		int sum1=0;
		for(int i=1;;i++) {
			if(i%2!=0) {
				sum1+=i;}
			else if(i%2==0) {
				sum1-=i;}
			if(sum1>=100){
				System.out.println(i); break;}
		//
		}
  
	}
}
