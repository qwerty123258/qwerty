package test1;

public class Student extends People {
	int studentnum;
	String major;
	
	
	public Student(String name, String personnum,int studentnum, String major) {
		super(name, personnum);//부모 클래스의 생성자.
		this.studentnum = studentnum;
		this.major = major;
	}
}
