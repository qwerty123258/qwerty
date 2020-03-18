package com.icia.myproject.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.myproject.dao.CommentDAO;
import com.icia.myproject.dto.CommentDTO;
import com.icia.myproject.page.Paging;

@Service
public class CommentService {
	
	@Autowired
	private CommentDAO dao;
	
	@Autowired
	private HttpSession session;

	public int countComment(String bno) {
		return dao.countComment(bno);
	}

	public List<CommentDTO> commentList(String bno, Paging paging) {
		List<CommentDTO> commentList = dao.commentList(bno,paging);
		return commentList;
	}

	public boolean addComments(CommentDTO comment) {
		String id= (String) session.getAttribute("id");
		comment.setId(id);
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