package com.icia.airandroom.dto;

import lombok.Data;

@Data
public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String email;
	private String phone;
	private String imgname;
	private String imgoriname;
	private String kakaoid;
	private String googleid;
	private String kind;
	private boolean checkAutoLogin;
}
