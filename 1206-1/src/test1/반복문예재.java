package test1;
import java.util.*;
public class 반복문예재 {

	public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      System.out.println("메시지를 입력하세요.");
      System.out.println("프로그램을 종료하려면 '종료'를 입력하세요");
      boolean open = true;
      while(open) {
      String message = null;
      String close = "종료";
      System.out.print(">");
      message = scan.next();
      if(message.equals(close)) open=false;
      if(!message.equals(close))
      System.out.println(message);
      if(!message.equals(close))
      System.out.println("다른 말도 씨부려보세요  계속 따라함");
      }
      
     

}
}
