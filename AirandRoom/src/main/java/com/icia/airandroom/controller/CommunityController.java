package com.icia.airandroom.controller;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.icia.airandroom.dto.InquireDTO;
import com.icia.airandroom.dto.ReportDTO;
import com.icia.airandroom.page.Paging;
import com.icia.airandroom.service.CommunityService;


@Controller
public class CommunityController {

	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private HttpSession session;

	private ModelAndView mav;
	
	
	@RequestMapping(value = "/goPopup", method = RequestMethod.GET)
	public String goPopup() {
		return "community/Popup";
	}
	
	@RequestMapping(value = "/goInquireForm", method = RequestMethod.GET)
	public ModelAndView goInquireForm(@RequestParam("id") String id) {
		mav=new ModelAndView();
		mav.addObject("otherid",id);
		mav.setViewName("community/InquireForm");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/sendInquireForm", method = RequestMethod.POST)
	public int sendInquireForm(@ModelAttribute InquireDTO inquire,HttpServletRequest request,MultipartHttpServletRequest mtfRequest) throws IllegalStateException, IOException {
		int result = communityService.sendInquireForm(inquire,request,mtfRequest);
		return result; 
	}
	
	@ResponseBody
	@RequestMapping(value = "/selectInquire", method = RequestMethod.GET)
	public Map<String,Object> selectInquire(@RequestParam("page") int page,@RequestParam("otherid") String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		Paging paging=new Paging();
		paging.setPage(1);
		int count=communityService.countInquire(id);
		paging.setTotalCount(count);
		map = communityService.selectInquire(id,paging);
		return map;
	 }
	
	@RequestMapping(value = "/myInquireList", method = RequestMethod.GET)
	public ModelAndView myInquireList(@RequestParam("page") int page) {
		mav=new ModelAndView();
		String id=(String) session.getAttribute("id");
		mav = communityService.myInquireList(id,page);
		return mav;
	 }
	
	
    @RequestMapping(value="/selectInquirePost", method=RequestMethod.GET)
    public ModelAndView selectInquirePost(@ModelAttribute InquireDTO inquire) throws IOException {
    	mav = new ModelAndView();
    	mav = communityService.selectInquirePost(inquire);   	
    	return mav;
    }
    
	
    @RequestMapping(value="/replyInquireForm", method=RequestMethod.GET)
    public ModelAndView replyInquireForm(@RequestParam("id") String id,@RequestParam("ino") String ino,@RequestParam("title") String title) throws IOException {
    	mav = new ModelAndView();
		mav.addObject("ino",ino);
		mav.addObject("id",id);
		mav.addObject("title",title);
		mav.setViewName("community/ReplyInquire"); 	
    	return mav;
    }
	
    @ResponseBody
    @RequestMapping(value="/replyInquire", method=RequestMethod.POST)
    public String replyInquire(@ModelAttribute InquireDTO inquire) throws IOException {
    	boolean result = communityService.replyInquire(inquire);
    	if(result) {
        	return "Success";
    	}
    	else {
    		return "Fail";
    	}
    }
	
    @RequestMapping(value="/replyView", method=RequestMethod.GET)
    public ModelAndView selectReplyInquire(@ModelAttribute InquireDTO inquire) throws IOException {
    	mav = new ModelAndView();
    	mav = communityService.selectReplyInquire(inquire);   	
    	return mav;
    }
	
    @RequestMapping(value="/myInquire", method=RequestMethod.GET)
    public ModelAndView myInquire(@ModelAttribute InquireDTO inquire) throws IOException {
    	mav = new ModelAndView();
    	mav = communityService.myInquire(inquire);   	
    	return mav;
    }
    
	@ResponseBody
	@RequestMapping(value = "/modifyInquireForm", method = RequestMethod.POST)
	public int modifyInquireForm(@ModelAttribute InquireDTO inquire,HttpServletRequest request,MultipartHttpServletRequest mtfRequest) throws IllegalStateException, IOException {
		int result = communityService.modifyInquireForm(inquire,request,mtfRequest);
		return result; 	
	}
	
	@ResponseBody
    @RequestMapping(value="/deleteInquire", method=RequestMethod.GET)
    public String deleteInquire(@ModelAttribute InquireDTO inquire) throws IOException {
    	boolean result = communityService.deleteInquire(inquire);
    	if(result) {
    		return "Success";
    	}
    	else {
    		return "Fail";
    	}
    }
	
	@RequestMapping(value = "/goReportForm", method = RequestMethod.GET)
	public ModelAndView goReportForm(@RequestParam("id") String id) {
		mav=new ModelAndView();
		mav.addObject("otherId",id);
		mav.setViewName("community/ReportForm");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/sendReportForm", method = RequestMethod.POST)
	public int sendReportForm(@ModelAttribute ReportDTO report,HttpServletRequest request,MultipartHttpServletRequest mtfRequest) throws IllegalStateException, IOException {
		int result = communityService.sendReportForm(report,request,mtfRequest);
		return result; 
	}
	
	@RequestMapping(value = "/selectReport", method = RequestMethod.GET)
	public ModelAndView selectReport(@ModelAttribute Paging paging) {
		mav = new ModelAndView();
		if(paging.getPage() != 1) {
			paging.setPage(paging.getPage());
		} 
		else {
			paging.setPage(1);
		}		
			int count=communityService.countReport();
			paging.setTotalCount(count);
			mav = communityService.selectReport(paging);
		return mav;
	 }
	
    @RequestMapping(value="/selectReportPost", method=RequestMethod.GET)
    public ModelAndView selectReportPost(@ModelAttribute ReportDTO report) throws IOException {
    	mav = new ModelAndView();
    	mav = communityService.selectReportPost(report);
    	return mav;
    }
	
    @ResponseBody
    @RequestMapping(value="/deleteReport", method=RequestMethod.GET)
    public String deleteReport(@ModelAttribute ReportDTO report) throws IOException {
    	boolean result = communityService.deleteReport(report);
    	if(result) {
    		return "Success";
    	}
    	else {
    		return "Fail";
    	}
    }
    
	@ResponseBody
	@RequestMapping(value = "/acceptReport", method = RequestMethod.POST)
	public int acceptReport(@ModelAttribute ReportDTO report) throws IllegalStateException, IOException {
		int result = communityService.acceptReport(report);
		return result; 		
	}
    
	@RequestMapping(value = "/filedownload", method = RequestMethod.GET)
	public void filedownload(@RequestParam("filename") String filename,HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(); 
		String root_path = session.getServletContext().getRealPath("/");
		String attach_path = "resources/fileUpload/";
		String savePath=root_path+""+attach_path+filename;
	    File downloadFile = new File(savePath); //그 경로 맞는 파일 객체 생성
	    FileInputStream inStream = new FileInputStream(downloadFile);  // 객체를 읽어들임
	    try {
			setDisposition(filename,request,response);// 파일 다운로드시 한글 처리
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
	
}


