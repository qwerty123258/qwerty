package com.icia.myproject.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {
	
	private String bno;
	private String title;
	private String id;
	private String contents;
	private String writedate;
	private String bview;
	
}
