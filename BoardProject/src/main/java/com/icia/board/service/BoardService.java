package com.icia.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board.dao.BfileDAO;
import com.icia.board.dao.BoardDAO;
import com.icia.board.dto.BoardDTO;
import com.icia.board.page.Paging;

@Service
public class BoardService {
	private ModelAndView mav;
	
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private BfileDAO fdao;

	public ModelAndView write(BoardDTO board) throws IllegalStateException, IOException {
		mav=new ModelAndView();
		int result=dao.write(board);
		if(result>0) {
			MultipartFile bfile = board.getBfile();
			String bfileoriname=bfile.getOriginalFilename();
			String savePath="C:\\Users\\5\\git\\qwerty\\BoardProject\\src\\main\\webapp\\resources\\fileUpload\\"+bfileoriname;
			if(!bfile.isEmpty()) {
				bfile.transferTo(new File(savePath));
			}
			board.setBfileoriname(bfileoriname);
			int bfileresult=fdao.fileUpload(board);
			if(bfileresult>0) {
				mav.setViewName("redirect:/Welcome");
			}
		}
		else {
			mav.setViewName("redirect:/Fail");
		}
		return mav;
	}

	public int countBoard() {
		
		return dao.countBoard();
	}

	public List<BoardDTO> boardList(Paging paging) {
		List<BoardDTO> boardList = dao.boardList(paging);
		return boardList;
	}

	public ModelAndView boardDetail(String bno, int page) {
		BoardDTO board=dao.boardDetail(bno);
		mav=new ModelAndView();
		mav.addObject("board", board);
		mav.addObject("page", page);
		mav.setViewName("board/BoardDetail");
		return mav;
	}

	public boolean boardDelete(String bno) {
		int result=dao.boardDelete(bno);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public ModelAndView boardModify(String bno, int page) {
		BoardDTO board=dao.boardModfiy(bno);
		mav=new ModelAndView();
		mav.addObject("board", board);
		mav.addObject("page", page);
		mav.setViewName("board/BoardModify");
		return mav;
	}

	public boolean boardUpdate(BoardDTO board) {
		int result=dao.boardUpdate(board);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

}
