package com.icia.myproject.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberDTO {
	
	private String id;
	private String pw;
	private String name;
	private String email;
	private String domain;
	private String emailaddress;
	private String kakaoid;
	private String naverid;
	private MultipartFile mfile;
	private String profileimg;
	private String oriprofileimg;

}
