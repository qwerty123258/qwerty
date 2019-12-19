package test1;
import java.util.*;

public class Bankmain {	
	 List<Bank> customerList= new ArrayList<Bank>(); //은행 고객의 정보를 모아두는 리스트.
	public static void main(String[] args) {
		 int command=0; //커낸드를 입력할때 사용하는 변수
		 Banksub sub= new Banksub();
		 int cunum=0; //로그인할때 사용하는 고객번호 변수.
		while(true) {
			sub.commandlist(); //커맨드 리스트를 보여주는 메서드
			try{
			command=sub.command(); //커맨드를 입력하는 메서드
			if(command==1) {
				sub.command1(); //객채 생성해서 리스트에 넣는 메서드
			}
			if(command==2) {
				cunum=sub.command2(); //각 객체마다 갖고 있는 고유한 값을 갖고 있는 고객번호를 입력하면 그 값을 메인의 cunum으로 반환해주는 메서드
			}
			if(command==3) {
				sub.command3(cunum); //2번메서드에서 반환 받은 값을 바탕으로 예금 기능 실행을 실행하는 메서드.
			}
			if(command==4)	{
				sub.command4(cunum); //2번 메서드에서 반환 받은 값을 바탕으로 출금 기능 실행을 실행하는 메서드.
			}
			if(command==5){
				sub.command5(cunum); //2번 메서드에서 반환 받은 값을 바탕으로 잔액조회 기능 실행을 실행하는 메서드.
			}	
			if(command==6){
				sub.command6(cunum); //2번 메서드에서 반환 받은 값을 바탕으로 상대방의 계좌번호와 입력한 계좌가 일치해야 이체기능을 실행하는 메서드.
			}
			if(command==7){
				sub.command7(); //현재 리스트에 저장된 모든 데이터를 출력해주는 메서드.
			}
				if(command!=1 &&command!=2 &&command!=3 &&command!=4 &&command!=5 &&command!=6 &&command!=7) {
					System.out.println("올바른 커맨드가 아닙니다.");
				}
				if(command==8) {
				System.out.println("종료되었습니다.");
				break;
				}
		}
			catch(Exception e) {
				System.out.println("잘못된 입력입니다.");
			}
		}
				}
}
