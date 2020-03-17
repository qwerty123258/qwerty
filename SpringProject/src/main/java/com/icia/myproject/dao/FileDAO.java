package com.icia.myproject.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.myproject.dto.BoardDTO;

@Repository
public class FileDAO {

	
	@Autowired
	private SqlSessionTemplate sql;

	public void fileUpload(BoardDTO board) {
		sql.insert("Board.upload", board);
		
	}





}
