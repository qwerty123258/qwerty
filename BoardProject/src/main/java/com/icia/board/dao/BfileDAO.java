package com.icia.board.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.board.dto.BoardDTO;

@Repository
public class BfileDAO {
	
	@Autowired
	private SqlSessionTemplate sql;

	public int fileUpload(BoardDTO board) {
		return sql.insert("Board.file", board);
		
	}

}
