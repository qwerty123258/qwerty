package test3;

public class Student {
	private int studentnum;
	private String name;
	private int age;
	private String address;
	private String gender;
	private String phone;
	
	
	public int getStudentnum() {
		return studentnum;
	}
	@Override
	public String toString() {
		return "Student [studentnum=" + studentnum + ", name=" + name + ", age=" + age + ", address=" + address
				+ ", gender=" + gender + ", phone=" + phone + "]";
	}
	public void setStudentnum(int studentnum) {
		this.studentnum = studentnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
