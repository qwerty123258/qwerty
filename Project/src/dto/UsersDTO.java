package dto;

public class UsersDTO {
	private String ID;
	private String pw;
	private String name;
	private String personno;
	private String email;
	private String blacklist;
	private String certify;
	private String grade;
	private String bfile;
	private String point;
	

	@Override
	public String toString() {
		return "UsersDTO [ID=" + ID + ", pw=" + pw + ", name=" + name + ", personno=" + personno + ", email=" + email
				+ ", blacklist=" + blacklist + ", certify=" + certify + ", grade=" + grade + ", bfile=" + bfile
				+ ", point=" + point + "]";
	}
	public String getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}
	public String getCertify() {
		return certify;
	}
	public void setCertify(String certify) {
		this.certify = certify;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getBfile() {
		return bfile;
	}
	public void setBfile(String bfile) {
		this.bfile = bfile;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPersonno() {
		return personno;
	}
	public void setPersonno(String personno) {
		this.personno = personno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
