package test1;
import java.util.*;
public class 은행기능구현 {

	public static void main(String[] args) {
		// 은행의 예금/출금/잔고/종료 기능 구현하기.
		Scanner scan = new Scanner(System.in);
		int inmoney=0; //입금할때 잔액에 더하기 위한 변수
		int savemoney=0; //현재 잔액 변수
		int command; // 1,2,3,4 명령 커맨드용 변수이면서 돈의 액수 입력할때 사용하는 변수
		for(;;) {
		System.out.println("------------------------");
		System.out.println("1.예금   2.출금   3.잔고   4.종료");
		System.out.println("------------------------");
		System.out.print("원하는 커맨드를 선택하세요. ");
		command=scan.nextInt();
		if(command!=1 && command!=2 && command!=3 && command!=4) {
			System.out.println("커맨드를 잘못 입력하였습니다.");
			
		}
		if(command==1) {
			System.out.println("입금할만큼의 돈을 입력하세요");
			command=scan.nextInt();
			inmoney=command;
			savemoney+=inmoney;
			System.out.println("입금후 잔액 :" + savemoney);
		}
		if(command==2) {
			command=scan.nextInt();
			if(savemoney>command) {
			savemoney=savemoney-command;
			System.out.println("출금 후 잔액 :"+ savemoney);
			}
			if(savemoney<command) {
				System.out.println("잔액이 부족합니다." + "\n"+ "현재 잔액 :" + savemoney);
			}
		}
		if(command==3) {
			System.out.println("잔고 :"+savemoney);
		}
		if(command==4) {
			break;
		}
		}
	}

}
