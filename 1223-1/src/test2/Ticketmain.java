package test2;

import java.util.Scanner;

public class Ticketmain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(true) {
		System.out.println("사전예약을 하셨나요?");
		System.out.println("--------------");
		System.out.println("1.네      | 2.아니오");
		System.out.println("--------------");
		int command=scan.nextInt();
		if(command==1) {
			System.out.println("언제 예약을 하셧나요?");
			System.out.println("---------------------------");
			System.out.println("1.30일 전      | 2.10일 전   | 3.5일 전");
			System.out.println("----------------------------");
			int command2=scan.nextInt();
			if(command2==1) {
				boolean d30=true;
				boolean d10=false;
				boolean d5=false;
				BookingTicket bt = new BookingTicket(0,d30,d10,d5);
				System.out.println("티켓 번호"+bt.ticketnum+" "+"가격 : "+bt.d30());
			}
			if(command2==2) {
					boolean d30=false;
					boolean d10=true;
					boolean d5=false;
					BookingTicket bt = new BookingTicket(0,d30,d10,d5);
					System.out.println("티켓 번호 : "+bt.ticketnum+" "+"가격 : "+bt.d10());
			}
				if(command2==3) {
					boolean d30=false;
					boolean d10=false;
					boolean d5=true;
					BookingTicket bt = new BookingTicket(0,d30,d10,d5);
					System.out.println("티켓 번호 : "+bt.ticketnum+" "+"가격 : "+bt.d5());
			}
		}
		if(command==2) {
			System.out.println("신용카드 결제이신가요?");
			System.out.println("---------------------------");
			System.out.println("1. 네                | 2. 아니오");
			System.out.println("----------------------------");
			int command3=scan.nextInt();
			if(command3==1) {
				boolean creditcard=true;
				NormalTicket nt = new NormalTicket(0,creditcard);
				System.out.println("티켓 번호 : "+nt.ticketnum+" "+"가격 : "+nt.creditcard());
			}
			if(command3==2) {
				boolean creditcard=false;
				NormalTicket nt = new NormalTicket(0,creditcard);
				System.out.println("티켓 번호 : "+nt.ticketnum+" "+"가격 : "+nt.price);
			}
		}
		}
	}

}