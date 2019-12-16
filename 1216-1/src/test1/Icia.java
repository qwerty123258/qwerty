package test1;

public class Icia {
	String name;
	int age;
	String gender;
	String address;
	
	
	
	Icia(String name, int age, String gender,String address){
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}
	public String toString() {
		return "Icia [name=" + name + ", age=" + age + ", gender=" + gender + ", address=" + address + "]";
	}
}
