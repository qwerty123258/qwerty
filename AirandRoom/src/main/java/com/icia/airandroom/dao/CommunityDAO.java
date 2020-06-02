package com.icia.airandroom.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.icia.airandroom.dto.InquireDTO;
import com.icia.airandroom.dto.ReportDTO;
import com.icia.airandroom.page.Paging;

@Repository
public class CommunityDAO {

	
	@Autowired
	private SqlSessionTemplate sql;
	
	public int sendInquireForm(InquireDTO inquire) {
		return sql.insert("Community.sendInquireForm", inquire);
	}
	
	
	public List<InquireDTO> selectInquire(String id,Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("otherid",id);
		map.put("paging",paging);
		return sql.selectList("Community.selectInquire",map);
	}
	
	public List<InquireDTO> selectInquireByUser(String id,Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("paging",paging);
		return sql.selectList("Community.selectInquireByUser",map);
	}

	public int countInquire(String id) {
		return sql.selectOne("Community.countInquire",id);
	}
	public int countInquireByUser(String id) {
		return sql.selectOne("Community.countInquireByUser",id);
	}

	public InquireDTO selectInquirePost(InquireDTO inquire) {
		return sql.selectOne("Community.selectInquirePost",inquire);
	}
	
	public int readByAdmin(InquireDTO inquire) {
		return sql.update("Community.readByAdmin",inquire);
	}
	
	public InquireDTO selectReplyInquire(InquireDTO inquire) {
		return sql.selectOne("Community.selectReplyInquire",inquire);
	}
	public int readByUser(InquireDTO inquire) {
		return sql.update("Community.readByUser",inquire);
	} 

	public int replyInquire(InquireDTO inquire) {
		return sql.update("Community.replyInquire",inquire);
	}
	public int replyInquireUpdate(InquireDTO inquire) {
		return sql.update("Community.replyInquireUpdate",inquire);
	}

	public int modifyInquireForm(InquireDTO inquire) {
		return sql.insert("Community.modifyInquireForm", inquire);
	}

	public int deleteInquire(InquireDTO inquire) {
		return sql.delete("Community.deleteInquire", inquire);		
	}
	public int deleteInquireUpdateByAdmin(InquireDTO inquire) {
		return sql.delete("Community.deleteInquireUpdateByAdmin", inquire);		
	}
	public int deleteInquireUpdate(InquireDTO inquire) {
		return sql.update("Community.deleteInquireUpdate",inquire);
	}
	
	public int sendReportForm(ReportDTO report) {
		return sql.insert("Community.sendReportForm", report);
	}
	
	public List<ReportDTO> selectReport(Paging paging) {
		return sql.selectList("Community.selectReport",paging);
	}
	public int countReport() {
		return sql.selectOne("Community.countReport");
	}
	
	public ReportDTO selectReportPost(ReportDTO report) {
		return sql.selectOne("Community.selectReportPost",report);
	}
	
	public int reportRead(ReportDTO report) {
		return sql.update("Community.reportRead",report);
	}
	
	public int deleteReport(ReportDTO report) {
		return sql.delete("Community.deleteReport", report);		
	}
	public int acceptReportUpdate(ReportDTO report) {
		Map<String,Object> map = new HashMap<String,Object>();
		int amount=60*60*24*1;
		Date limit =new Date(System.currentTimeMillis() + (1000*amount));
		map.put("limit", limit);
		map.put("id", report.getOtherid());
		sql.update("Member.loginban", map);
		return sql.update("Community.acceptReportUpdate",report);
	}

	public int countMyInquire(String id) {
		return sql.selectOne("Community.countMyInquire", id);
	}


	public InquireDTO myInquire(InquireDTO inquire) {
		return sql.selectOne("Community.myInquire", inquire);
	}
}
