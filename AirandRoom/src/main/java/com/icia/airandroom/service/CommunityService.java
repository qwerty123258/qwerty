package com.icia.airandroom.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.icia.airandroom.dao.CommunityDAO;
import com.icia.airandroom.dto.InquireDTO;
import com.icia.airandroom.dto.ReportDTO;
import com.icia.airandroom.page.Paging;

@Service
public class CommunityService {

	
	private ModelAndView mav;

	@Autowired
	private CommunityDAO cdao;
	
	private String upload(String imgoriname, byte[] bytes, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession(); 
		String root_path = session.getServletContext().getRealPath("/");
		String attach_path = "resources/fileUpload/";
		String savePath=root_path+""+attach_path;
		UUID uuid=UUID.randomUUID();
		String savefilename=uuid+"_"+imgoriname;
		File target=new File(savePath,savefilename);
		FileCopyUtils.copy(bytes,target);
		return savefilename;	
	}
	
	public int sendInquireForm(InquireDTO inquire, HttpServletRequest request, MultipartHttpServletRequest mtfRequest) throws IllegalStateException, IOException {
    	String imgoriname=mtfRequest.getFile("inquiryfile").getOriginalFilename();
    	String imgname=upload(imgoriname,mtfRequest.getFile("inquiryfile").getBytes(),request);
		inquire.setIfileoriname(imgoriname);
		inquire.setIfile(imgname);
		int result = cdao.sendInquireForm(inquire);	
		return result;
	}
	
	
	public Map<String, Object> selectInquire(String id,Paging paging) {
		Map<String, Object> map = new HashMap<String,Object>();
			List<InquireDTO> inquireList = cdao.selectInquire(id,paging);
			map.put("list",inquireList);
			map.put("paging",paging);		
		return map;
	}

	public int countInquire(String id) {
		int count=cdao.countInquire(id);
		return count;
	}
	public int countInquireByUser(String id) {
		int count=cdao.countInquireByUser(id);
		return count;
	}

	public ModelAndView selectInquirePost(InquireDTO inquire) {
		mav = new ModelAndView();
		InquireDTO inquirePost = cdao.selectInquirePost(inquire);
		cdao.readByAdmin(inquire);
		mav.addObject("selectInquirePost",inquirePost);
		mav.setViewName("community/SelectInquirePost");
		return mav;
	}
	
	
	public ModelAndView replyInquireForm(InquireDTO inquire) {
		mav = new ModelAndView();
		cdao.replyInquireForm(inquire);
		mav.addObject("inquire",inquire);
		mav.setViewName("community/ReplyInquire");
		return mav;
	}

	public boolean replyInquire(InquireDTO inquire) throws IllegalStateException, IOException {      
        int insertResult = cdao.replyInquire(inquire);
        if(insertResult > 0) {
        	return true;
        } 
        else {
        	return false;
        }
	}

	public ModelAndView selectReplyInquire(InquireDTO inquire) {
		mav = new ModelAndView();
		InquireDTO replyInquire = cdao.selectReplyInquire(inquire);
		mav.addObject("reply",replyInquire);
		mav.setViewName("community/ReplyView");	
		return mav;
	}



	public int modifyInquireForm(InquireDTO inquire,HttpServletRequest request,MultipartHttpServletRequest mtfRequest) throws IllegalStateException, IOException {
				
    	String imgoriname=mtfRequest.getFile("inquiryfile").getOriginalFilename();
    	String imgname=upload(imgoriname,mtfRequest.getFile("inquiryfile").getBytes(),request);
		inquire.setIfileoriname(imgoriname);
		inquire.setIfile(imgname);
		
		int result = cdao.modifyInquireForm(inquire);
		
		return result;
	}

	public boolean deleteInquire(InquireDTO inquire) {
		int result = cdao.deleteInquire(inquire);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public int sendReportForm(ReportDTO report,HttpServletRequest request,MultipartHttpServletRequest mtfRequest) throws IllegalStateException, IOException {		
    	String imgoriname=mtfRequest.getFile("reportfile").getOriginalFilename();
    	String imgname=upload(imgoriname,mtfRequest.getFile("reportfile").getBytes(),request);
    	report.setRfileoriname(imgoriname);
    	report.setRfile(imgname);
		int result = cdao.sendReportForm(report);	
		return result;
	}

	
	public ModelAndView selectReport(ReportDTO report,Paging paging,String kind) {
		mav = new ModelAndView();
		if(kind.matches("admin")) {
			List<ReportDTO> reportList = cdao.selectReport(report,paging);
			mav.addObject("reportList",reportList);
			mav.addObject("paging",paging);
			mav.setViewName("community/SelectReport");	
		} else {
			List<ReportDTO> reportListByUser = cdao.selectReportByUser(report, paging);
			mav.addObject("reportList",reportListByUser);
			mav.addObject("paging",paging);
			mav.setViewName("community/SelectReport");				
		}		
		return mav;
	}
	
	
	public int countReport(ReportDTO report) {
		int count=cdao.countReport(report);
		return count;
	}
	public int countReportByUser(ReportDTO report) {
		int count=cdao.countReportByUser(report);
		return count;
	}

	public ModelAndView selectReportPost(ReportDTO report,String kind) {
		mav = new ModelAndView();
		if(kind.matches("admin")) {
			ReportDTO reportPost = cdao.selectReportPost(report);
			int result = cdao.reportRead(report);
			mav.addObject("selectReportPost",reportPost);
			mav.setViewName("community/SelectReportPost");
		} else {
			ReportDTO reportPost = cdao.selectReportPost(report);
			mav.addObject("selectReportPost",reportPost);
			mav.setViewName("community/SelectReportPost");
		}
		return mav;
	}
	
	
	public int modifyReportForm(ReportDTO report,HttpServletRequest request,MultipartHttpServletRequest mtfRequest) throws IllegalStateException, IOException {
    	String imgoriname=mtfRequest.getFile("reportfile").getOriginalFilename();
    	String imgname=upload(imgoriname,mtfRequest.getFile("reportfile").getBytes(),request);
    	report.setRfileoriname(imgoriname);
    	report.setRfile(imgname);
		int result = cdao.modifyReportForm(report);
		
		return result;
	}

	public ModelAndView deleteReport(ReportDTO report,String kind) {
		mav = new ModelAndView();
		
	     	int result = cdao.deleteReport(report);
			if (result > 0) {
				mav.setViewName("community/SelectReport");
			} else {
				mav.setViewName("community/SelectReport");
			}
		return mav;
	}

	public int acceptReport(ReportDTO report) {	
		int result=cdao.acceptReportUpdate(report);
		return result;
	}

	public void reportReset(String id) {
		cdao.reportReset(id);
	}

	public ModelAndView myInquireList(String id, int page) {
		mav= new ModelAndView();
		int count=cdao.countMyInquire(id);
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(count);
		List<InquireDTO> myInquireList = cdao.selectInquireByUser(id, paging);
		mav.addObject("paging", paging);
		mav.addObject("myInquireList", myInquireList);
		mav.setViewName("community/MyInquireList");
		return mav;
	}

	public ModelAndView myInquire(InquireDTO inquire) {
		mav= new ModelAndView();
		InquireDTO inq = cdao.myInquire(inquire);
		mav.addObject("inquire", inq);
		mav.setViewName("community/MyInquire");
		return mav;
	}
}
