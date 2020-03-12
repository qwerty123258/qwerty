package com.icia.member.dto;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String kakaoid;
	private String naverid;
	
	
	public String getKakaoid() {
		return kakaoid;
	}


	public void setKakaoid(String kakaoid) {
		this.kakaoid = kakaoid;
	}


	public String getNaverid() {
		return naverid;
	}


	public void setNaverid(String naverid) {
		this.naverid = naverid;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", kakaoid=" + kakaoid
				+ ", naverid=" + naverid + "]";
	}
}
