package test1;
import java.util.*;
public class Membermain {

	public static void main(String[] args) {
		Scanner scan =new Scanner(System.in);
		Sql sql = new Sql(); //쿼리문 객체
		List<Member> memberList = new ArrayList<Member>(); //ID,비밀번호 확인후 쿼리문 실행해야하므로 리스트에 저장
		for(;;) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("1.DB접속 | 2.회원등록 | 3.(관리자) 전체 조회 | 4.정보 변경 | 5.회원 탈퇴 | 6.종료 ");
			System.out.println("--------------------------------------------------------------------------");
			int command=scan.nextInt(); //커맨드 입력용
			if(command==1) {
				sql.Connection(); //DB연결 메소드
				memberList=sql.List(); //DB에 저장된 데이터를 리스트에 담아서 우선 가져옴(관리자 ID, 본인확인용 ID구별 위해서..)
			}
			if(command==2) {
				sql.SignUp(memberList); //DB에 일반 데이터(회원)추가하는 메소드
				memberList=sql.List(); //등록한뒤에 리스트 갱신
			}
			if(command==3) {
				sql.Admin(memberList); //관리자용 전체 조회 메소드
			}
			if(command==4) {
				sql.UpdateMain(memberList); //데이터 수정하는 메소드
				memberList=sql.List(); //수정한뒤에 리스트 갱신
			}
			if(command==5) {
				sql.DeleteMain(memberList); //데이터 삭제하는 메소드
				memberList=sql.List(); //삭제한뒤에 리스트 갱신
			}
			if(command==6) {
				break;
			}
			
		}

	}
}
