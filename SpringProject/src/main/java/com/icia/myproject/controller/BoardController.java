package com.icia.myproject.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.icia.myproject.page.Paging;
import com.icia.myproject.dto.CommentDTO;
import com.icia.myproject.service.CommentService;
import com.icia.myproject.dto.BoardDTO;
import com.icia.myproject.service.BoardService;

@Controller
public class BoardController {
	private ModelAndView mav;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentService commentService;
	
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
	
	@RequestMapping(value = "/goBoardList", method = RequestMethod.GET)
	public String goBoardList() {
		return "board/BoardList";
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
	@RequestMapping(value = "/SearchList", method = RequestMethod.GET)
	public Map<String, Object> searchList(@RequestParam("page") int page,@RequestParam("searchOpt") String searchOpt,@RequestParam("keyword") String keyword,@ModelAttribute BoardDTO board) {
		Paging paging = new Paging();
		board.setKeyword(keyword);
		board.setSearchOpt(searchOpt);
		System.out.println(searchOpt);
		List<BoardDTO> boardList = null;
		if(board.getSearchOpt().equals("title")) {
			int count=boardService.countTitleBoard(board);
			if(page<0) {
				page=1;
			}
	        paging.setPage(page);
	        paging.setTotalCount(count);
			boardList = boardService.searchTitleList(board,paging);
		}
		if(board.getSearchOpt().equals("writer")) {
			int count=boardService.countWriterBoard(board);
			if(page<0) {
				page=1;
			}
	        paging.setPage(page);
	        paging.setTotalCount(count);
			boardList = boardService.searchWriterList(board,paging);
		}
		if(board.getSearchOpt().equals("titlecontents")) {
			int count=boardService.countTitleContentsBoard(board);
			if(page<0) {
				page=1;
			}
	        paging.setPage(page);
	        paging.setTotalCount(count);
			boardList = boardService.searchTitleContentsList(board,paging);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", boardList);
		map.put("paging", paging);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/BoardListOrder", method = RequestMethod.GET)
	public Map<String, Object> boardListOrder(@RequestParam("page") int page) {
		Paging paging = new Paging();
		int count=boardService.countBoard();
		if(page<0) {
			page=1;
		}
        paging.setPage(page);
        paging.setTotalCount(count);
		List<BoardDTO> boardListOrder = boardService.boardListOrder(paging);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", boardListOrder);
		map.put("paging", paging);
		return map;
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
	
	@RequestMapping(value = "/Update", method = RequestMethod.POST)
	public ModelAndView boardUpdate(@ModelAttribute BoardDTO board,MultipartHttpServletRequest mtfRequest,@RequestParam("page") int page) throws IOException {
		boolean result=boardService.boardUpdate(board,mtfRequest);
		mav=new ModelAndView();	
		if(result) {
			mav.addObject("page",page);
			mav.addObject("bno", board.getBno());
			mav.setViewName("redirect:/BoardDetail");
			return mav;
		}
		else {
			mav.addObject("page",page);
			mav.addObject("bno", board.getBno());
			mav.setViewName("redirect:/BoardDetail");
			return mav;
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
	
	@RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
	public void fileDownload(@RequestParam("bfile") String bfile,@RequestParam("bfileoriname") String bfileoriname,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String savePath="C:\\Users\\5\\git\\qwerty\\SpringProject\\src\\main\\webapp\\resources\\fileUpload\\"+bfile;
	    File downloadFile = new File(savePath); //그 경로 맞는 파일 객체 생성
	    FileInputStream inStream = new FileInputStream(downloadFile);  // 객체를 읽어들임
	    try {
			setDisposition(bfileoriname,request,response);// 파일 다운로드시 한글 처리
		} catch (Exception e) {
			e.printStackTrace();
		}
	    OutputStream outStream = response.getOutputStream(); //객체를 쓰기함
	     
	    byte[] buffer = new byte[4096]; 
	    int bytesRead = -1;
	     
	    while ((bytesRead = inStream.read(buffer)) != -1) {
	        outStream.write(buffer, 0, bytesRead);
	    }
	    inStream.close();
	    outStream.close();     
		}
	
    private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String browser = getBrowser(request); //현재 브라우저 리턴 받기
        String dispositionPrefix = "attachment; filename="; // 값 초기화
        String encodedFilename = null; //인코딩된 파일 이름
        if (browser.equals("MSIE")) {
               encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Trident")) {       // IE11 문자열 깨짐 방지
               encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Firefox")) {
               encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
               encodedFilename = URLDecoder.decode(encodedFilename);
        } else if (browser.equals("Opera")) {
               encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
               StringBuffer sb = new StringBuffer();
               for (int i = 0; i < filename.length(); i++) {
                      char c = filename.charAt(i);
                      if (c > '~') {
                            sb.append(URLEncoder.encode("" + c, "UTF-8"));
                      } else {
                            sb.append(c);
                      }
               }
               encodedFilename = sb.toString();
        } else if (browser.equals("Safari")){
               encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
               encodedFilename = URLDecoder.decode(encodedFilename);
        }
        else {
               encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";

        }
        response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
        if ("Opera".equals(browser)){
               response.setContentType("application/octet-stream;charset=UTF-8");
        }
}
    
	public String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
   if (header.indexOf("MSIE") > -1) {
       return "MSIE";
   } else if (header.indexOf("Trident") > -1) {   // IE11 문자열 깨짐 방지
       return "Trident";
   } else if (header.indexOf("Chrome") > -1) {
       return "Chrome";
   } else if (header.indexOf("Opera") > -1) {
       return "Opera";
   } else if (header.indexOf("Safari") > -1) {
       return "Safari";
   }
   return "Firefox";
  }
		
	}
