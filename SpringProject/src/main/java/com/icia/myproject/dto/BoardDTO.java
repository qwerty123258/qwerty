package com.icia.myproject.dto;

import lombok.Data;

@Data
public class BoardDTO {
	
	private String bno;
	private String title;
	private String id;
	private String contents;
	private String writedate;
	private String bview;
	private String bfno;
	private String bfilename;
	private String bfileoriname;
	private String currentBno;
	private String beforeFilename;
	private String beforeOriname;
	private String delfilename;
}
