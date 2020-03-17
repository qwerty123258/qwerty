package com.icia.board.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {
	private String bno;
	private String title;
	private String writedate;
	private String bview;
	private String id;
	private String contents;
	
	private MultipartFile bfile;
	private String bfilename;
	private String bfileoriname;
}
