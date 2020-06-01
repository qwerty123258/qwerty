package com.icia.airandroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.airandroom.dao.ReportDAO;
import com.icia.airandroom.dto.ReportDTO;
import com.icia.airandroom.page.Paging;



@Service
public class ReportService {

	
	private ModelAndView mav;
	
	@Autowired
	private ReportDAO rdao;

	public int countReport() {
		
		return rdao.countReport();
	}

	public ModelAndView reportList(Paging paging) {
		List<ReportDTO> reportList=rdao.reportList(paging);
		mav=new ModelAndView();
		mav.addObject("reportList",reportList);
		mav.addObject("paging", paging);
		mav.setViewName("report/ReportList");
		return mav;
	}
}
