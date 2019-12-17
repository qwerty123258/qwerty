package test1;

import java.util.Scanner;

public class Gas {
	int gas; // 가스라는 필드 선언
	
	void setGas(int gas) {
		this.gas=gas; //메인에서 가스를 가져와서 필드에 대입, 메인에서 주유소처럼 가스를 넣어주면 필드에 가스가 대입이 되고 boolean Leftgas() 메서드에서 판단후 run()메서드 실행 가스가 0이 되면 종료.
	}
	
	boolean isLeftGas() { //남아있는 가스 판단하는 메서드
		if(gas==0) { //남아있는 가스가 0이면 false를 반환함 >>>run()메서드가 종료됨.
			System.out.println("gas가 없습니다.");
			return false;
		}
			else { //남아있는 가스가 0이 아니면 true를 반환함.>>>>run() 메서드가 시작됨.
				System.out.println("gas가 있습니다.");
				return true;
			}
		}
	
	void run() {
		while(true){
			if(gas>0) { //가스가 0보다 크면 차가 달리면서 가스는 줄어들고
				System.out.println("달립니다.(gas 잔량 : "+gas+")");
				gas-=1;
			}
			else { //차가 달리다가 가스가 떨어지면 , 즉 가스가 없으면  run() 메소드 종료. >>주행 종료.
				System.out.println("멈춥니다.(gas 잔량 : "+gas+")");
				return;
			}
		}
	}
	
	void input() {
		
	Scanner scan=new Scanner(System.in); //입력하기위해서
	System.out.println("주입할 가스의 양을 입력하세요.");
	setGas(scan.nextInt()); //가스를 내가 입력한 숫자만큼 넣어줌
	boolean savegas=isLeftGas(); //자동차 객체내부 메서드에서 내가 넣은 가스를 판단해주고 수치에 따라 true or false를 반환해줌
	if(savegas) { //true로 반환되면 차가 달리고 false로 반환되면 차가 멈춤. run() 이 멈춤.
		run();
	}
	scan.close();
}
	
	}