package test2;
import java.util.*;
public class TryCatch {

	public static void main(String[] args) {
		ArrayList<Integer> ar = new ArrayList<Integer>();
		ar.add(5);
		ar.add(8);
		ar.add(6);
			for(int i=0; i<= ar.size(); i++) {
				try {
				System.out.println(ar.get(i));
				}
				catch(IndexOutOfBoundsException e){
					System.out.println("예외처리"); //IndexOutOfBoundsException 예외가 발생하였지만 중지 하지 않고 대신 실행할 코드를 실행.
					e.printStackTrace(); //어떤 에러가 어떤 과정으로 발생하였는지 보여주는 메서드
				}
				finally {
					System.out.println("무조건 실행"); //IndexOutOfBoundsException 예외가 아님에도 출력.
				}
			}

	}

}
