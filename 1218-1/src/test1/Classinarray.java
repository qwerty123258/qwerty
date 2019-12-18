package test1;

import java.util.Scanner;

public class Classinarray {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("저장해야 되는 회원정보의 수를 입럭하세요");
		Navermember[] navermember=new Navermember[scan.nextInt()];
		//Navermember 타입의 데이터를(객체) navermember이라는 일종의 배열 변수에 넣는다.
		for(int i=0; i<navermember.length; i++) {
			navermember[i] = new Navermember(id(),password(),name(),birth(),gender(),email(),phone());
			System.out.println(navermember[i]);
		}
		System.out.println(navermember[0].getId());
	}
	public static String id() {
		Scanner scan = new Scanner(System.in);
		System.out.println("ID를 입력하세요.");
		String id=scan.next();
		return id;
	}
	public static String password() {
		Scanner scan = new Scanner(System.in);
		System.out.println("비밀번호를 입력하세요.");
		String password=scan.next();
		return password;
	}
	public static String name() {
		Scanner scan = new Scanner(System.in);
		System.out.println("이름을 입력하세요.");
		String name=scan.next();
		return name;
	}
	public static String birth() {
		Scanner scan = new Scanner(System.in);
		System.out.println("생년월일을 입력하세요.");
		String birth=scan.next();
		return birth;
	}
	public static String gender() {
		Scanner scan = new Scanner(System.in);
		System.out.println("성별을 입력하세요.");
		String gender=scan.next();
		return gender;
	}
	public static String email() {
		Scanner scan = new Scanner(System.in);
		System.out.println("이메일 주소를 입력하세요.");
		String email=scan.next();
		return email;
	}
	public static String phone() {
		Scanner scan = new Scanner(System.in);
		System.out.println("연락처를 입력하세요.");
		String phone=scan.next();
		return phone;
	}

	}

