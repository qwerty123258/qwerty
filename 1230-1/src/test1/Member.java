package test1;

public class Member {
	private String id;
	private String password;
	private String name;
	private String birth;
	private String gender;
	private String email;
	private String phone;
	private String day;

	

	
	@Override
	public String toString() {
		return "Member [ID=" + id + ", 비밀번호=" + password + ", 이름=" + name + ", 생년월일=" + birth + ", 성별="
				+ gender + ", 이메일 주소=" + email + ", 연락처=" + phone + ", 가입일=" + day + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public String getGender() {
		return gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDay() {
		return day;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setDay(String day) {
		this.day = day;
	}
}
