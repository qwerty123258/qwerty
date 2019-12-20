package test1;
import java.util.*;
public class Studentsub {
	List<Student> studentList = new ArrayList<Student>();
	List<Integer>javascoreList = new ArrayList<Integer>();
	List<Integer> servletscoreList = new ArrayList<Integer>();
	List<Integer> springscoreList = new ArrayList<Integer>();
	Student student=null;
	int studentnumber=1;
	
	public void commandlist() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("1.학생등록 |2.과목별 점수입력 |3.점수리스트  |4.과목별 최고점수 |5.과목별 최저점수 | 6.종료 ");
		System.out.println("-------------------------------------------------------------------------------");
		
	}
	public int command() {
		Scanner scan = new Scanner(System.in);
		int command=scan.nextInt();
		return command;
	}
	public void studentAdd() {
		student = new Student(studentnumber,nameInput(), birthInput(), addressInput(),phonenumberInput());
		studentnumber++;
		studentList.add(student);
	}
	public String nameInput() { //이름을 입력하는 메서드
		Scanner scan = new Scanner(System.in);
		System.out.println("이름을 입력하세요.");
		String name=scan.next();
		return name;
	}
	public String birthInput() { //생년월일을 입력하는 메서드
		Scanner scan = new Scanner(System.in);
		System.out.println("생년월일을 입력하세요.");
		System.out.println("ex)94/05/28");
		String birth=scan.next();
		return birth;
	}
	public String addressInput() { //주소를 입력하는 메서드
		Scanner scan = new Scanner(System.in);
		System.out.println("사는 지역을 입력하세요.");
		System.out.println("ex)인천,부천");
		String address=scan.next();
		return address;
	}
	public String phonenumberInput() { //연락처를 입력하는 메서드.
		Scanner scan = new Scanner(System.in);
		System.out.println("연락처를 입력하세요.");
		System.out.println("ex)010-1111-1234");
		String phonenumber=scan.next();
		return phonenumber;
	}
	public void scoreInput() {
		Scanner scan= new Scanner(System.in);
		System.out.println("학생의 이름을 입력하세요.");
		String studentname=scan.next();
		System.out.println("java 점수를 입력하세요.");
		int javascore=scan.nextInt();
		System.out.println("servlet 점수를 입력하세요.");
		int servletscore=scan.nextInt();
		System.out.println("spring 점수를 입력하세요.");
		int springscore=scan.nextInt();
		javascoreList.add(javascore);
		servletscoreList.add(servletscore);
		springscoreList.add(springscore);
		for(int i=0; i<studentList.size(); i++) {
			if(studentList.get(i).getStudentName().equals(studentname)) {
				studentList.get(i).setJavaScore(javascore);
				studentList.get(i).setServletScore(servletscore);
				studentList.get(i).setSpringScore(springscore);
			}
		}
	}
	public void showScoreList() {
		for(int i=0; i<studentList.size(); i++) {
			System.out.print("학생번호 :"+studentList.get(i).getStudentNumber()+" ");
			System.out.print("이름 :"+studentList.get(i).getStudentName()+" ");
			System.out.print("생년월일 :"+studentList.get(i).getStudentBirth()+" ");
			System.out.print("주소 :"+studentList.get(i).getStudentAddress()+" ");
			System.out.print("연락처 :"+studentList.get(i).getStudentPhoneNumber()+" ");
			System.out.print("Java점수 :"+studentList.get(i).getJavaScore()+" ");
			System.out.print("Servlet점수:"+studentList.get(i).getServletScore()+" ");
			System.out.println("Spring점수 :"+studentList.get(i).getSpringScore());
		}
	}
	public void maxScore() {
		Scanner scan= new Scanner(System.in);
		System.out.println("----------------------------------------------------");
		System.out.println("1.Java | 2.Servlet | 3.Spring");
		System.out.println("----------------------------------------------------");
		System.out.println("조회하실 과목을 선택하세요.");
		int subjectcommand=scan.nextInt();
		if(subjectcommand==1) {
			int max=Integer.MIN_VALUE; //최고점수 출력용
			String name=null;//최고득점자 이름 출력용
			for(int i=0; i<studentList.size(); i++) {
				if(studentList.get(i).getJavaScore()>max) {
					name=studentList.get(i).getStudentName();
					max=studentList.get(i).getJavaScore();
					studentList.get(i).setJavaScore(max);
				}
			}
			Collections.sort(javascoreList);
			System.out.println(javascoreList);
			System.out.println("Java 최고 점수는 "+name+"의 "+max+"점 입니다.");
		}
		if(subjectcommand==2) {
			int max=Integer.MIN_VALUE; //최고점수 출력용
			String name=null;//최고득점자 이름 출력용
			for(int i=0; i<studentList.size(); i++) {
				if(studentList.get(i).getServletScore()>max) {
					name=studentList.get(i).getStudentName();
					max=studentList.get(i).getServletScore();
					studentList.get(i).setServletScore(max);
				}
			}
			Collections.sort(servletscoreList);
			System.out.println(servletscoreList);
			System.out.println("Servlet 최고 점수는 "+name+"의 "+max+"점 입니다.");
		}
		if(subjectcommand==3) {
			int max=Integer.MIN_VALUE; //최고점수 출력용
			String name=null;//최고득점자 이름 출력용
			for(int i=0; i<studentList.size(); i++) {
				if(studentList.get(i).getSpringScore()>max) {
					name=studentList.get(i).getStudentName();
					max=studentList.get(i).getSpringScore();
					studentList.get(i).setSpringScore(max);
				}
			}
			Collections.sort(springscoreList);
			System.out.println(springscoreList);
			System.out.println("Spring 최고 점수는 "+name+"의 "+max+"점 입니다.");
		}
		else if((subjectcommand!=1 && subjectcommand!=2 && subjectcommand!=3)) {
			System.out.println("올바른 커맨드가 아닙니다.");
		}
	}
	public void minScore() {
		Scanner scan= new Scanner(System.in);
		System.out.println("----------------------------------------------------");
		System.out.println("1.Java | 2.Servlet | 3.Spring");
		System.out.println("----------------------------------------------------");
		System.out.println("조회하실 과목을 선택하세요.");
		int subjectcommand=scan.nextInt();
		if(subjectcommand==1) {
			int min=Integer.MAX_VALUE; //최저점수 출력용
			String name=null;//최저득점자 이름 출력용
			for(int i=0; i<studentList.size(); i++) {
				if(studentList.get(i).getJavaScore()<min) {
					name=studentList.get(i).getStudentName();
					min=studentList.get(i).getJavaScore();
					studentList.get(i).setJavaScore(min);
				}
			}
			 Collections.sort(javascoreList, Collections.reverseOrder());
			System.out.println(javascoreList);
			System.out.println("Java 최저 점수는 "+name+"의 "+min+"점 입니다.");
		}
		if(subjectcommand==2) {
			int min=Integer.MAX_VALUE; //최저점수 출력용
			String name=null;//최저득점자 이름 출력용
			for(int i=0; i<studentList.size(); i++) {
				if(studentList.get(i).getServletScore()<min) {
					name=studentList.get(i).getStudentName();
					min=studentList.get(i).getServletScore();
					studentList.get(i).setServletScore(min);
				}
			}
			 Collections.sort(servletscoreList, Collections.reverseOrder());
			System.out.println(servletscoreList);
			System.out.println("Servlet 최저 점수는 "+name+"의 "+min+"점 입니다.");
		}
		if(subjectcommand==3) {
			int min=Integer.MAX_VALUE; //최저점수 출력용
			String name=null;//최저득점자 이름 출력용
			for(int i=0; i<studentList.size(); i++) {
				if(studentList.get(i).getSpringScore()<min) {
					name=studentList.get(i).getStudentName();
					min=studentList.get(i).getSpringScore();
					studentList.get(i).setSpringScore(min);
				}
			}
			 Collections.sort(springscoreList, Collections.reverseOrder());
			System.out.println(springscoreList);
			System.out.println("Spring 최저 점수는 "+name+"의 "+min+"점 입니다.");
		}
		else if((subjectcommand!=1 && subjectcommand!=2 && subjectcommand!=3)) {
			System.out.println("올바른 커맨드가 아닙니다.");
		}
	}
	}
