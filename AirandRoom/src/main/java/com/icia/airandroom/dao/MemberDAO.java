package com.icia.airandroom.dao;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.airandroom.dto.MemberDTO;
import com.icia.airandroom.page.Paging;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sql;

	public boolean checkID(String id) {
		int count=sql.selectOne("Member.checkID", id);
		if(count>0) {
			return true;
		}
		return false;
	}

	public boolean createMember(MemberDTO member) {
		if(member.getKakaoid()!=null) {
			int result=sql.insert("Member.createKakaoMember", member);
			if(result>0) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(member.getGoogleid()!=null) {
			int result=sql.insert("Member.createGoogleMember", member);
			if(result>0) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			int result=sql.insert("Member.createMember", member);
			if(result>0) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	public boolean checkEmail(String email) {
		int count=sql.selectOne("Member.checkEmail", email);
		if(count>0) {
			return true;
		}
		return false;
	}

	public boolean login(MemberDTO member) {
		int count=sql.selectOne("Member.login", member);
		if(count>0) {
			return true;
		}
		return false;
	}

	public boolean searchID(MemberDTO member) {
		String id=sql.selectOne("Member.searchID", member);
		if(id!=null) {
			member.setId(id);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean searchPW(MemberDTO member) {
		String pw=sql.selectOne("Member.searchPW", member);
		if(pw!=null) {
			member.setPw(pw);
			return true;
		}
		else {
			return false;
		}
	}

	public void searchKind(MemberDTO member) {
		String kind=sql.selectOne("Member.searchKind", member);
		member.setKind(kind);
	}

	public boolean deleteMember(String id, HttpServletRequest request) {
		
			MemberDTO member=sql.selectOne("Member.currentPic",id);//4월 27일 회원탈퇴 전 프로필 사진 삭제 위해 수정
    		HttpSession session = request.getSession(); 
    		String root_path = session.getServletContext().getRealPath("/");
    		String attach_path = "resources/fileUpload/";
    		String savePath=root_path+""+attach_path+""+member.getImgname();
    		File f = new File(savePath);
    		 if(f.exists()){
    		 f.delete(); 
    		}
    		 //여기까지
			int result=sql.delete("Member.deleteMember",id);
			if(result>0) {
				return true;
			}
			else {
				return false;
			}
	}

	public MemberDTO modifyMembers(String id) {
		return sql.selectOne("Member.modifyMember", id);
	}

	public boolean updateMember(MemberDTO member) {
		int result=sql.update("Member.updateMember", member);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public List<MemberDTO> memberList(Paging paging) {
		List<MemberDTO> memberList= sql.selectList("Member.memberList",paging);
		return memberList;
	}

	public int countMember() {
		int count=sql.selectOne("Member.countMember");
		return count;
	}

	public boolean emailUpdate(MemberDTO member) {
		int result=sql.update("Member.emailUpdate", member);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String currentEmail(String id) {
		return sql.selectOne("Member.currentEmail", id);
	}
	
	public String googleIdCheck(String googleid) {
		return sql.selectOne("Member.googleIdCheck",googleid);
	}
	
	public MemberDTO kakaoLogin(String id) {
		return sql.selectOne("Member.kakaoLogin", id);
	}

	public int kakaoCheck(String id) {
		return sql.selectOne("Member.kakaoCheck", id);
	}
	
    public void keepLogin(String uid, String sessionId, Date next){
        
        Map<String, Object> map =new HashMap<String,Object>();
        map.put("userId", uid);
        map.put("sessionId", sessionId);
        map.put("next", next);
         
        // Mapper.xml로 데이터를 전달할 때 한 객체밖에 전달 못함으로 map으로 묶어서 보내줌 단... 주의할 점은
        // Mapper.xml 안에서 #{} 이 안에 지정한 이름이랑 같아야함.. 자동으로 매핑될 수 있도록
        // 아래가 수행되면서, 사용자 테이블에 세션id와 유효시간이 저장됨
        sql.update("Member.keepLogin",map);
         
    }
 
    // 이전에 로그인한 적이 있는지, 즉 유효시간이 넘지 않은 세션을 가지고 있는지 체크한다.
    public MemberDTO checkUserWithSessionKey(String sessionId){
        // 유효시간이 남아있고(>now()) 전달받은 세션 id와 일치하는 사용자 정보를 꺼낸다.
        return sql.selectOne("Member.checkUserWithSessionKey",sessionId);
     
    }

	public boolean checkperiod(MemberDTO member) {
		int count=sql.selectOne("Member.checkperiod", member);
		if(count>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public String usersEmail(String id) {
		return sql.selectOne("Member.usersEmail", id);
	}

}
