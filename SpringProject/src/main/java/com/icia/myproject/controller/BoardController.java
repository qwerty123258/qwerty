package com.icia.myproject.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.icia.myproject.dto.BoardDTO;
import com.icia.myproject.service.BoardService;

@Controller
public class BoardController {
	private ModelAndView mav;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/Write", method = RequestMethod.POST)
	public ModelAndView write(@ModelAttribute BoardDTO board,MultipartHttpServletRequest mtfRequest) throws IllegalStateException, IOException {
		mav=new ModelAndView();
		mav=boardService.write(board,mtfRequest);
		return mav;
	}
	
	@RequestMapping(value = "/Fail", method = RequestMethod.GET)
	public String fail() {
		return "board/Fail";
	}
}
