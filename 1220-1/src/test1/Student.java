package test1;

public class Student {
	int studentNumber;
	String studentName;
	String studentBirth;
	String studentAddress;
	String studentPhoneNumber;
	int javaScore;
	int springScore;
	int servletScore;
	
	public Student(int studentNumber, String studentName, String studentBirth, String studentAddress,
			String studentPhoneNumber) {
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.studentBirth = studentBirth;
		this.studentAddress = studentAddress;
		this.studentPhoneNumber = studentPhoneNumber;
	}

	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentBirth() {
		return studentBirth;
	}

	public void setStudentBirth(String studentBirth) {
		this.studentBirth = studentBirth;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentPhoneNumber() {
		return studentPhoneNumber;
	}

	public void setStudentPhoneNumber(String studentPhoneNumber) {
		this.studentPhoneNumber = studentPhoneNumber;
	}

	public int getJavaScore() {
		return javaScore;
	}

	public void setJavaScore(int javaScore) {
		this.javaScore = javaScore;
	}

	public int getSpringScore() {
		return springScore;
	}

	public void setSpringScore(int springScore) {
		this.springScore = springScore;
	}

	public int getServletScore() {
		return servletScore;
	}

	public void setServletScore(int servletScore) {
		this.servletScore = servletScore;
	}
	
}
