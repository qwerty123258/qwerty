package com.icia.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.board.dto.BoardDTO;
import com.icia.board.page.Paging;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sql;

	public int write(BoardDTO board) {

		return sql.insert("Board.write", board);
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

}
