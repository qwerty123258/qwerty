package test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banksub {
	 int customernumber = 1; //고객 번호의초기값
	 int accountnumber =110; //계좌 번호의 초기값.
	 Bank customer=null; //은행 객체.
	 List<Bank> customerList= new ArrayList<Bank>(); //은행 고객의 정보를 모아두는 리스트.
	 public void commandlist() {
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println("  1.고객등록 | 2.로그인 | 3.예금하기 | 4.출금하기 | 5.잔액조회 | 6.이체    | 7.전체확인  | 8.종료                                  ");
			System.out.println("---------------------------------------------------------------------------------");
	 }
	public int command() {
		Scanner scan = new Scanner(System.in);
		int command = scan.nextInt();
		return command;
	}
	public String nameInput() { //이름을 입력하는 메서드
		Scanner scan = new Scanner(System.in);
		System.out.println("이름을 입력하세요.");
		String name=scan.next();
		return name;
	}
	public int moneyInput() { //고객 등록시 입금할 금액을 입력하는 메서드
		Scanner scan = new Scanner(System.in);
		System.out.println("금액을 입력하세요.");
		int money=scan.nextInt();
		return money;
	}
	public void command1() {
		customer = new Bank(nameInput(),accountnumber,customernumber, moneyInput());
		customerList.add(customer);
		customernumber++; //객체가 생성되면 고객번호를 증가시켜 고객이 증가함에 따라 1~n이 되게 하기 위함.
		accountnumber++; //계좌도 마찬가지.
	}
	public int command2() {
		Scanner scan = new Scanner(System.in);
		System.out.println("고객번호를 입력하세요.");
		int cunum=scan.nextInt();
		System.out.println("본인의 계좌번호를 입력하세요.");
		int inputacnum=scan.nextInt();
		for(int i=0; i<customerList.size(); i++) {
			int acnum=customerList.get(i).getAccountNumber();//저장되어 있는 계좌번호를 확인
			if(acnum==inputacnum) {
				if(inputacnum==customerList.get(cunum-1).getAccountNumber()) {
					System.out.println("로그인 성공!"+"\n"+customerList.get(cunum-1).getName()+"님 환영합니다.");
					return cunum;
				}
			}
		}
		//본인의 고객번호와 계좌번호가 다르면(고객번호를 ID라고 생각하고...)
		cunum=0; //입력한 고객번호를 초기화하고 리턴
		System.out.println("로그인 실패.");
		return cunum;
		}
	public void command3(int cunum) {
		Scanner scan = new Scanner(System.in);
		if(cunum==customerList.get(cunum-1).getCustomerNumber()) {
		customerList.get(cunum-1).setMoney(moneyInput());
		System.out.println("입금 후 잔액 : "+customerList.get(cunum-1).getMoney());
	}
	}
	public void command4(int cunum) {
		Scanner scan = new Scanner(System.in);
		if(cunum==customerList.get(cunum-1).getCustomerNumber()) {
			int money=moneyInput();
			if(customerList.get(cunum-1).getMoney()>=money) {
				customerList.get(cunum-1).setminusMoney(money);
				System.out.println("출금후 잔액 :"+customerList.get(cunum-1).getMoney());
			}
		else {System.out.println("잔액이 부족합니다."); System.out.println("현재 잔액 :"+customerList.get(cunum-1).getMoney());}
	}
	}
	public void command5(int cunum) {
		if(cunum==customerList.get(cunum-1).getCustomerNumber()) {
		System.out.println("현재 잔액 :"+customerList.get(cunum-1).getMoney());
	}
	}
	public void command6(int cunum) {
		Scanner scan = new Scanner(System.in);
		if(cunum==customerList.get(cunum-1).getCustomerNumber()) {
		System.out.println("이체할 사람의 계좌번호를 입력하세요.");
		int acnum=scan.nextInt(); //입력한 계좌번호를 받는 변수
		 for(int i=0; i<customerList.size(); i++) {
			int otheracnum =customerList.get(i).getAccountNumber(); //타인의 계좌번호를 저장하는 변수.
			if(acnum==otheracnum) { //저장된 계좌번호와 입력한 계좌번호를 비교
				if(customerList.get(i).getAccountNumber()!=customerList.get(cunum-1).getAccountNumber()) {
					System.out.println("이체할 금액을 입력하세요.");
					int transmoney=scan.nextInt(); //이체할 금액을 받는 변수
					if(customerList.get(cunum-1).getMoney()>transmoney) {
						customerList.get(cunum-1).setminusMoney(transmoney);
						System.out.println("이체하고 남은 잔액 : "+customerList.get(cunum-1).getMoney());
						customerList.get(i).setMoney(transmoney);
						System.out.println("상대방의 잔액 : "+customerList.get(i).getMoney());
					}
					else {
						System.out.println("잔액이 부족합니다."); System.out.println("현재 잔액 :"+customerList.get(cunum-1).getMoney());
					}
				}
				else if(customerList.get(i).getAccountNumber()==customerList.get(cunum-1).getAccountNumber()) {
					System.out.println("본인 계좌로는 이체가 불가능합니다.");
					System.out.println("입력한 계좌번호는 "+acnum+"이고 본인 계좌번호는 "+customerList.get(i).getAccountNumber()+"입니다.");
				}
			}
		 }
		}
	}
	public void command7() {
		for(int i=0; i<customerList.size(); i++) {
		System.out.print("고객번호 :" + customerList.get(i).getCustomerNumber()+" ");
		System.out.print("이름 :"+customerList.get(i).getName()+" ");
		System.out.print("계좌번호 :"+customerList.get(i).getAccountNumber()+" ");
		System.out.println("잔액 :"+customerList.get(i).getMoney());
	}
		
	}
}
