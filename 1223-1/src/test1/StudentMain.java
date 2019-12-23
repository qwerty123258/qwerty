package test1;

public class StudentMain {

	public static void main(String[] args) {
		Student student = new Student("전석종","940528-1",2019,"JAVA");
		System.out.println(student.name);
		System.out.println(student.personnum);
		System.out.println(student.studentnum);
		System.out.println(student.major);
	}

}
