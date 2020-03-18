package com.icia.myproject.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.icia.myproject.dao.BoardDAO;
import com.icia.myproject.dto.BoardDTO;
import com.icia.myproject.page.Paging;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO bdao;
	
	@Autowired
	private HttpSession session;
	
	private ModelAndView mav;

	public ModelAndView write(BoardDTO board, MultipartHttpServletRequest mtfRequest) throws IOException {
		mav= new ModelAndView();
		String id=(String) session.getAttribute("id");
		board.setId(id);
		int writeresult=bdao.write(board);
		if(writeresult>0) {
			List<MultipartFile> fileList = mtfRequest.getFiles("bfile");
	        for(int i=0; i<fileList.size(); i++) {
	        	String borifile=fileList.get(i).getOriginalFilename();
	        	String bfilename=upload(borifile,fileList.get(i).getBytes());
	        	board.setBfile(bfilename);
	        	board.setBfileoriname(borifile);
	        	bdao.fileUpload(board);
	        }
	        mav.setViewName("redirect:/home");
		}
		else {
	        mav.setViewName("redirect:/Fail");
		}
		return mav;
	}
	private String upload(String borifile, byte[] bytes) throws IOException {
		String savePath="C:\\Users\\5\\git\\qwerty\\SpringProject\\src\\main\\webapp\\resources\\fileUpload\\";
		UUID uuid=UUID.randomUUID();
		String savefilename=uuid+"_"+borifile;
		File target=new File(savePath,savefilename);
		FileCopyUtils.copy(bytes,target);
		return savefilename;	
	}
	public int countBoard() {
		return bdao.countBoard();
	}
	public List<BoardDTO> boardList(Paging paging) {
		List<BoardDTO> boardList = bdao.boardList(paging);
		return boardList;
	}
	
	public ModelAndView boardDetail(String bno, int page) {
		BoardDTO board=bdao.boardDetail(bno);
		List<BoardDTO> fileList=bdao.fileList(bno);
		mav=new ModelAndView();
		mav.addObject("board", board);
		mav.addObject("page", page);
		mav.addObject("fileList", fileList);
		System.out.println(fileList.toString());
		mav.setViewName("board/BoardDetail");
		return mav;
	}

	public boolean boardDelete(String bno) {
		int result=bdao.boardDelete(bno);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public ModelAndView boardModify(String bno, int page) {
		BoardDTO board=bdao.boardModfiy(bno);
		mav=new ModelAndView();
		mav.addObject("board", board);
		mav.addObject("page", page);
		mav.setViewName("board/BoardModify");
		return mav;
	}

	public boolean boardUpdate(BoardDTO board) {
		int result=bdao.boardUpdate(board);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

}
