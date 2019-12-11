package main;
import java.util.Scanner;
public class Main6 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean close = true;
		while(close) {
			String userinput = scan.next();
			int computer=0;
		String computeroutput = null;
		if(userinput.equals("종료")) close=false;
		computer= (int) ((Math.random() * 3) +1);
		if(computer==1) 
		{computeroutput = "가위";}
		else if(computer==2)
		{computeroutput = "바위";}
		else if(computer==3) 
		{computeroutput = "보";}
		if(!userinput.equals("가위")) {
			if(!userinput.equals("바위")) {
				if(!userinput.equals("보")) {
					if(!userinput.equals("종료"))
						System.out.println("잘못 입력하셨습니다.");			
				}		
			}		
		}
		switch(computeroutput) {
		case "가위" :
			if(userinput.equals("가위")) {System.out.println("비겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput);}
			else if(userinput.equals("바위")) {System.out.println("이겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput);}
			else if(userinput.equals("보")) {System.out.println("졌습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput);}
		break;
		case "바위" :
		if(userinput.equals("가위")) {System.out.println("졌습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput);}
		else if(userinput.equals("바위")) {System.out.println("비겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput);}
		else if(userinput.equals("보")) {System.out.println("이겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput);}
		break;
		case "보" : 
		if(userinput.equals("가위")) {System.out.println("이겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput);}
		else if(userinput.equals("바위")) {System.out.println("졌습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput);}
		else if(userinput.equals("보")) {System.out.println("비겼습니다."); System.out.println("컴퓨터가 낸 것은? :"+ computeroutput);}
		break;
		}		
		}		
	}	
	}


