package com.icia.airandroom.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.airandroom.dto.ReportDTO;
import com.icia.airandroom.page.Paging;

@Repository
public class ReportDAO {

	@Autowired
	private SqlSessionTemplate sql;

	public int countReport() {
		int count=sql.selectOne("Report.countReport");
		return count;
	}

	public List<ReportDTO> reportList(Paging paging) {
		List<ReportDTO> reportList=sql.selectList("Report.reportList", paging);
		return reportList;
	}
}
