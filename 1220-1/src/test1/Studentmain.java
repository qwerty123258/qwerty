package test1;
public class Studentmain {

	public static void main(String[] args) {
		Studentsub sub= new Studentsub(); //메서드 모음용 클래스.
		int command; //커맨드용 변수.
		while(true) {
			sub.commandlist(); //커맨드 리스트를 보여주는 메서드
			try {
		command=sub.command(); //커맨드를 입력하고 메인으로 반환해주는 메서드
		if(command==1) {
			sub.studentAdd(); //학생을 추가해주는 메서드
		}
		if(command==2) {
			sub.scoreInput(); //학생을 추가할때 입력한 이름에 맞게 입력할시 점수가 입력되는 메서드
		}
		if(command==3) {
			sub.showScoreList(); //전체 리스트에 담긴 정보를 보여주는 메서드
		}
		if(command==4) {
			sub.maxScore(); //과목별 최고점수를 보여주고 득점자가 누구인지 알려주는 메서드.
		}
		if(command==5) {
			sub.minScore(); //과목별 최저점수를 보여주고 누구인지 알려주는 메서드.
		}
		if(command==6) {
			System.out.println("종료되었습니다.");
			break;
		}
		else if(command!=1 && command!=2 && command!=3 && command!=4 && command!=5 && command!=6) {
			System.out.println("올바른 커맨드가 아닙니다.");
		}
	}
			catch(Exception e){
				System.out.println("잘못된 입력입니다.");
			}
	}
}
}
