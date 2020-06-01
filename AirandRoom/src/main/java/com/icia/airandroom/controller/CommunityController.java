package com.icia.airandroom.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ModelAndView myInquireList(@RequestParam("page") int page,@RequestParam("id") String id) {
		mav=new ModelAndView();
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
    public ModelAndView replyInquireForm(@ModelAttribute InquireDTO inquire) throws IOException {
    	mav = new ModelAndView();
    	mav = communityService.replyInquireForm(inquire);   	
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
	public ModelAndView selectReport(@ModelAttribute ReportDTO report, @ModelAttribute Paging paging,@RequestParam("kind") String kind) {
		mav = new ModelAndView();
		if(paging.getPage() != 1) {
			paging.setPage(paging.getPage());
		} else {
			paging.setPage(1);
		}		
		if (kind.matches("admin")) {
			int count=communityService.countReport(report);
			paging.setTotalCount(count);
			mav = communityService.selectReport(report,paging,kind);
		}
		else {
			int count=communityService.countReportByUser(report);
			paging.setTotalCount(count);
			mav = communityService.selectReport(report,paging,kind);
		}		
		return mav;
	 }

    @RequestMapping(value="/selectReportPost", method=RequestMethod.GET)
    public ModelAndView selectReportPost(@ModelAttribute ReportDTO report,@RequestParam("kind") String kind) throws IOException {
    	mav = new ModelAndView();
    	mav = communityService.selectReportPost(report,kind);
    	return mav;
    }
    
	@ResponseBody
	@RequestMapping(value = "/modifyReportForm", method = RequestMethod.POST)
	public int modifyReportForm(@ModelAttribute ReportDTO report,HttpServletRequest request,MultipartHttpServletRequest mtfRequest) throws IllegalStateException, IOException {
		int result = communityService.modifyReportForm(report,request,mtfRequest);
		return result; 
		
		
	}
	
    @RequestMapping(value="/deleteReport", method=RequestMethod.GET)
    public ModelAndView deleteReport(@ModelAttribute ReportDTO report,@RequestParam("kind") String kind) throws IOException {
    	mav = new ModelAndView();
    	mav = communityService.deleteReport(report,kind);   	
    	return mav;
    }
    
	@ResponseBody
	@RequestMapping(value = "/acceptReport", method = RequestMethod.POST)
	public int acceptReport(@ModelAttribute ReportDTO report) throws IllegalStateException, IOException {
		int result = communityService.acceptReport(report);
		return result; 		
	}
	
}


