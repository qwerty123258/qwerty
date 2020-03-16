package com.icia.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.board.dao.CommentDAO;
import com.icia.board.dto.CommentDTO;
import com.icia.board.page.Paging;

@Service
public class CommentService {
	
	@Autowired
	private CommentDAO dao;

	public int countComment(String bno) {
		return dao.countComment(bno);
	}

	public List<CommentDTO> commentList(String bno, Paging paging) {
		List<CommentDTO> commentList = dao.commentList(bno,paging);
		return commentList;
	}

	public boolean addComments(CommentDTO comment) {
		int result=dao.addComments(comment);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean deleteComment(String cno) {
		int result=dao.delectComment(cno);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean UpdateComment(CommentDTO comment) {
		int result=dao.updateComment(comment);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

}
