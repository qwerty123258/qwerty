package test1;

public class Navermember {
	private String id; // 아이디
	private String password; //비밀번호
	private String name; // 이름
	private String birth;//생년월일
	private String gender;//성별
	private String email;//이메일 주소
	private String phone; // 연락처
	
	public Navermember(String id,String password,String name,String birth, String gender, String email, String phone){
		// 필드는  private이여도 생성자는 공개가 되어있어 생성자를 통해서 필드에 값은 저장이 됨.
		this.id=id;
		this.password=password;
		this.name=name;
		this.birth=birth;
		this.gender=gender;
		this.email=email;
		this.phone=phone;
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

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getId() {
		return id;
		
	}
	
	public void setId(String id) {
		this.id=id;
	}
}
