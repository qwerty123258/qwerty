package com.icia.myproject.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.myproject.dto.BoardDTO;
import com.icia.myproject.page.Paging;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sql;

	public int write(BoardDTO board) {
		return sql.insert("Board.write", board);
	}

	public void fileUpload(BoardDTO board) {
		sql.insert("Board.upload", board);
		
	}

	public int currentBno() {
		return sql.selectOne("Board.current");
	}

	public int countBoard() {
		return sql.selectOne("Board.count");
	}

	public List<BoardDTO> boardList(Paging paging) {
		return sql.selectList("Board.list",paging);
	}
	
	public BoardDTO boardDetail(String bno) {
		sql.insert("Board.bview", bno);
		return sql.selectOne("Board.detail",bno);
	}

	public int boardDelete(String bno) {
		return sql.delete("Board.delete", bno);
	}

	public BoardDTO boardModfiy(String bno) {
		return sql.selectOne("Board.detail",bno);
	}

	public int boardUpdate(BoardDTO board) {
		return sql.update("Board.update", board);
	}

	public List<BoardDTO> fileList(String bno) {
		return sql.selectList("Board.fileList", bno);
	}

}
