package com.icia.myproject.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.myproject.dto.FileDTO;

@Repository
public class FileDAO {

	
	@Autowired
	private SqlSessionTemplate sql;



	public void fileUpload(FileDTO bfiles) {
		sql.insert("Board.upload", bfiles);
	}

}
