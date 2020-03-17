package com.icia.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.CommentDTO;
import com.icia.board.page.Paging;
import com.icia.board.service.BoardService;
import com.icia.board.service.CommentService;

@Controller
public class HomeController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentService commentService;
	
	private ModelAndView mav;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value = "/Welcome", method = RequestMethod.GET)
	public String welcome() {
		
		return "home";
	}
	
	@RequestMapping(value = "/Fail", method = RequestMethod.GET)
	public String fail() {
		
		return "board/Fail";
	}
	
	@RequestMapping(value = "/goBoardList", method = RequestMethod.GET)
	public String boardList() {
		
		return "board/BoardList";
	}
	
	@RequestMapping(value = "/BoardWrite", method = RequestMethod.GET)
	public String boardWrite() {
		
		return "board/BoardWrite";
	}
	
	@RequestMapping(value = "/Write", method = RequestMethod.POST)
	public ModelAndView write(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		mav=new ModelAndView();
		mav=boardService.write(board);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BoardList", method = RequestMethod.GET)
	public Map<String, Object> boardList(@RequestParam("page") int page) {
		Paging paging = new Paging();
		int count=boardService.countBoard();
		if(page<0) {
			page=1;
		}
        paging.setPage(page);
        paging.setTotalCount(count);
		List<BoardDTO> boardList = boardService.boardList(paging);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", boardList);
		map.put("paging", paging);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/UpdateComment", method = RequestMethod.POST)
	public String updateComment(@ModelAttribute CommentDTO comment) {
		boolean result=commentService.UpdateComment(comment);
		if(result) {
			return "Success";
		}
		else {
			return "False";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/DeleteComment", method = RequestMethod.POST)
	public String deleteComment(@RequestParam("cno") String cno) {
		boolean result=commentService.deleteComment(cno);
		if(result) {
			return "Success";
		}
		else {
			return "False";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/CommentList", method = RequestMethod.GET)
	public Map<String, Object> commentList(@RequestParam("bno") String bno,@RequestParam("page") int page) {
		Paging paging = new Paging();
		int count=commentService.countComment(bno);
		if(page<0) {
			page=1;
		}
        paging.setPage(page);
        paging.setTotalCount(count);
		List<CommentDTO> commentList = commentService.commentList(bno,paging);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("comment", commentList);
		map.put("count", count);
		map.put("paging", paging);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/AddComments", method = RequestMethod.POST)
	public String addComments(@ModelAttribute CommentDTO comment) {
		boolean result=commentService.addComments(comment);
		if(result) {
			return "Success";
		}
		else {
			return "False";
		}
		
	}
	
	@RequestMapping(value = "/BoardDetail", method = RequestMethod.GET)
	public ModelAndView boardDetail(@RequestParam("bno") String bno,@RequestParam("page") int page) {
		mav=new ModelAndView();	
		mav=boardService.boardDetail(bno,page);
		return mav;
	}
	
	@RequestMapping(value = "/goModify", method = RequestMethod.GET)
	public ModelAndView boardModify(@RequestParam("bno") String bno,@RequestParam("page") int page) {
		mav=new ModelAndView();	
		mav=boardService.boardModify(bno,page);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/DeleteBoard", method = RequestMethod.GET)
	public String boardDelete(@RequestParam("bno") String bno) {
		boolean result=boardService.boardDelete(bno);
		if(result) {
			return "true";
		}
		else {
			return "false";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/Update", method = RequestMethod.POST)
	public String boardUpdate(@ModelAttribute BoardDTO board) {
		boolean result=boardService.boardUpdate(board);
		if(result) {
			return "true";
		}
		else {
			return "false";
		}
	}
	
}
