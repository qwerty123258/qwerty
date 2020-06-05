package com.icia.airandroom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.airandroom.dto.CommentsDTO;
import com.icia.airandroom.dto.RoomrevDTO;
import com.icia.airandroom.page.Paging;

@Repository
public class RoomrevDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	
	public List<RoomrevDTO> reviewList(String rno, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rno", rno);
		map.put("paging", paging);
		return sql.selectList("Roomrev.reviewList", map);
	}

	public int countRev(String rno) {
		return sql.selectOne("Roomrev.countRev",rno);
	}

	public int reviewWrite(RoomrevDTO roomrev) {
		return sql.insert("Roomrev.reviewWrite",roomrev);
	}

	public void roomrevPicWrite(RoomrevDTO roomrev) {
		sql.insert("Roomrev.roomrevPicWrite", roomrev);
	}

	public List<RoomrevDTO> myReviewList(String id, Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("paging", paging);
		List<RoomrevDTO> list=sql.selectList("Roomrev.myReviewList", map);
		return list;
	}


	public int myReviewListCount(String id) {
		return sql.selectOne("Roomrev.myReviewListCount", id);
	}

	public RoomrevDTO reviewUpdateForm(String revno) {
		return sql.selectOne("Roomrev.reviewUpdateForm", revno);
	}

	public List<RoomrevDTO> revpicList(String revno) {
		return sql.selectList("Roomrev.revpicList", revno);
	}

	public int reviewUpdate(RoomrevDTO roomrev) {
		return sql.update("Roomrev.reviewUpdate", roomrev);
	}

	public void revpicUpdateWrite(RoomrevDTO roomrev) {
		sql.insert("Roomrev.revpicUpdateWrite", roomrev);
	}

	public List<RoomrevDTO> myReviewPicList(String id,Paging paging) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("paging", paging);
		return sql.selectList("Roomrev.myReviewPicList", map);
	}

	public void revpicDelete(String rimgname) {
		sql.delete("Roomrev.revpicDelete", rimgname);
	}

	public int reviewDelete(String revno) {
		return sql.delete("Roomrev.reviewDelete",revno);
	}

	public int reviewProperty(RoomrevDTO roomrev) {
		return sql.update("Roomrev.reviewProperty", roomrev);
	}

	public String reviewPropertySelect(String revno) {
		return sql.selectOne("Roomrev.reviewPropertySelect", revno);
	}
	
	
	public List<CommentsDTO> reviewCommentListPaging(Paging paging, String revno, String opt) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paging", paging);
		map.put("revno", revno);
		map.put("opt", opt);
		return sql.selectList("Roomrev.reviewCommentListPaging", map);
	}

	public int reviewCommentWrite(CommentsDTO comments) {
		return sql.insert("Roomrev.reviewCommentWrite", comments);
	}

	public int reviewCommentDelete(String replyno) {
		return sql.delete("Roomrev.reviewCommentDelete", replyno);
	}

	public RoomrevDTO reviewView(String revno) {
		return sql.selectOne("Roomrev.reviewView", revno);
	}

	public int countComment(String revno) {
		return sql.selectOne("Roomrev.countComment",revno);
	}

	public List<RoomrevDTO> revpicGet(String revno) {
		return sql.selectList("Roomrev.revpicGet",revno);
	}

	public boolean writeCommentModal(Map<String, Object> map) {
		int result=sql.insert("Roomrev.writeCommentModal", map);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public int allReviewListCount(String rno) {
		// TODO Auto-generated method stub
		return sql.selectOne("Roomrev.allReviewListCount", rno);
	}

	public List<RoomrevDTO> allReviewList(String rno, Paging paging) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("paging", paging);
		map.put("rno", rno);
		return sql.selectList("Roomrev.allReviewList", map);
	}

	public void grade(RoomrevDTO roomrev) {
		// TODO Auto-generated method stub
		sql.insert("Roomrev.grade",roomrev);
	}

}
