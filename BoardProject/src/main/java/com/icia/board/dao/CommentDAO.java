package com.icia.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.board.dto.CommentDTO;
import com.icia.board.page.Paging;

@Repository
public class CommentDAO {
	
	@Autowired
	private SqlSessionTemplate sql;

	public int countComment(String bno) {
		return sql.selectOne("Comment.count", bno);
	}

	public List<CommentDTO> commentList(String bno, Paging paging) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("paging", paging);
			map.put("bno", bno);
			return sql.selectList("Comment.list", map);

	}

	public int addComments(CommentDTO comment) {
		return sql.insert("Comment.add", comment);
	}

	public int delectComment(String cno) {
		return sql.delete("Comment.delete", cno);
	}

	public int updateComment(CommentDTO comment) {
		return sql.update("Comment.update", comment);
	}

}
