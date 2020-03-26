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
			if(fileList.get(0).getSize()!= 0){
	        for(int i=0; i<fileList.size(); i++) {
	        	String borifile=fileList.get(i).getOriginalFilename();
	        	String bfilename=upload(borifile,fileList.get(i).getBytes());
	        	board.setBfilename(bfilename);
	        	board.setBfileoriname(borifile);
	        	bdao.fileUpload(board);
	        }
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
		mav.setViewName("board/BoardDetail");
		return mav;
	}
	
	public ModelAndView boardDetail(String bno, int page, String keyword, String searchOpt) {
		BoardDTO board=bdao.boardDetail(bno);
		List<BoardDTO> fileList=bdao.fileList(bno);
		mav=new ModelAndView();
		mav.addObject("board", board);
		mav.addObject("page", page);
		mav.addObject("keyword", keyword);
		mav.addObject("searchOpt", searchOpt);
		mav.addObject("fileList", fileList);
		mav.setViewName("board/BoardDetail");
		return mav;
	}

	public boolean boardDelete(String bno) {
		List<BoardDTO> fileList=bdao.fileList(bno);
		for(int i=0; i<fileList.size(); i++) {
			String deleteFiles=fileList.get(i).getBfilename();
	    		String savePath="C:\\Users\\5\\git\\qwerty\\SpringProject\\src\\main\\webapp\\resources\\fileUpload\\"+deleteFiles;
	    		File f = new File(savePath);
	    		 if(f.exists()){
	    		 f.delete(); 
	    		}
		}
		int result=bdao.boardDelete(bno);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public ModelAndView boardModify(String bno, int page, String keyword, String searchOpt) {
		BoardDTO board=bdao.boardModfiy(bno);
		List<BoardDTO> fileList=bdao.fileList(bno);
		mav=new ModelAndView();
		mav.addObject("board", board);
		mav.addObject("page", page);
		mav.addObject("fileList", fileList);
		mav.addObject("keyword", keyword);
		mav.addObject("searchOpt", searchOpt);
		mav.setViewName("board/BoardModify");
		return mav;
	}
	public boolean boardUpdate(BoardDTO board, MultipartHttpServletRequest mtfRequest) throws IOException {
		String beforefilename[]=mtfRequest.getParameterValues("beforeFilename");
		String bfno[]=mtfRequest.getParameterValues("bfno");
		String delfilename[]=mtfRequest.getParameterValues("delfilename");
			List<MultipartFile> fileList = mtfRequest.getFiles("bfile");
			if(fileList.get(0).getSize()!= 0){
	        	bdao.allFileDelete(board);
	        for(int i=0; i<fileList.size(); i++) {
	        	String borifile=fileList.get(i).getOriginalFilename();
	        	String bfilename=upload(borifile,fileList.get(i).getBytes());
	        	board.setBfilename(bfilename);
	        	board.setBfileoriname(borifile);
	        	bdao.fileUpdate(board);
	        }
	        if(mtfRequest.getParameterValues("bfno")!=null) {
	        for(int i=0; i<bfno.length; i++) {
	    		String savePath="C:\\Users\\5\\git\\qwerty\\SpringProject\\src\\main\\webapp\\resources\\fileUpload\\"+beforefilename[i];
	    		File f = new File(savePath);
	    		 if(f.exists()){
	    		 f.delete(); 
	    		}}}}
		else {
			if(mtfRequest.getParameterValues("delfilename")!=null) {
				for(int i=0; i<delfilename.length; i++) {
					bdao.fileDelete(delfilename[i]);
		    		String savePath="C:\\Users\\5\\git\\qwerty\\SpringProject\\src\\main\\webapp\\resources\\fileUpload\\"+delfilename[i];
		    		File f = new File(savePath);
		    		 if(f.exists()){
		    		 f.delete(); 
		    		}}}}
		int result=bdao.boardUpdate(board);
		if(result>0) {return true;}
		else {return false;}
	}
	public List<BoardDTO> boardListOrder(Paging paging) {
		List<BoardDTO> boardListOrder = bdao.boardListOrder(paging);
		return boardListOrder;
	}
	
	public int countTitleBoard(BoardDTO board) {
		return bdao.countTitleBoard(board);
	}
	public int countWriterBoard(BoardDTO board) {
		return bdao.countWriterBoard(board);
	}
	public int countTitleContentsBoard(BoardDTO board) {
		return bdao.countTitleContentsBoard(board);
	}
	public List<BoardDTO> searchTitleList(BoardDTO board, Paging paging) {
		return bdao.searchTitleList(board,paging);
	}
	public List<BoardDTO> searchWriterList(BoardDTO board, Paging paging) {
		return bdao.searchWriterList(board,paging);
	}
	public List<BoardDTO> searchTitleContentsList(BoardDTO board, Paging paging) {
		return bdao.searchTitleContentsList(board,paging);
	}

}
