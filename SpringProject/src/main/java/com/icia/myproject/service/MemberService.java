package com.icia.myproject.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.myproject.dao.MemberDAO;
import com.icia.myproject.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO mdao;
	
	private ModelAndView mav;

	public boolean checkMember(String id) {
		boolean result=mdao.checkMember(id);
		if(result) {
			return true;
		}
		else {
			return false;
		}
	}

	public ModelAndView createMember(MemberDTO member) throws IOException {
		mav=new ModelAndView();
		MultipartFile mfile = member.getMfile();
		if(!mfile.isEmpty()) {
			String fileoriname=mfile.getOriginalFilename();
			member.setOriprofileimg(fileoriname);
			String savefilename=upload(fileoriname,mfile.getBytes());
			member.setProfileimg(savefilename);
		}
		String domain =member.getDomain();
		String email= member.getEmail();
		String emailaddress=email+"@"+domain;
		member.setEmailaddress(emailaddress);
		int result=mdao.createMember(member);
		if(result>0) {
			mav.setViewName("redirect:/home");
		}
		else {
			mav.setViewName("redirect:/fail");
		}
		return mav;
	}

	private String upload(String fileoriname, byte[] bytes) throws IOException {
		String savePath="C:\\Users\\5\\git\\qwerty\\SpringProject\\src\\main\\webapp\\resources\\fileUpload\\";
		UUID uuid=UUID.randomUUID();
		String savefilename=uuid+"_"+fileoriname;
		File target=new File(savePath,savefilename);
		FileCopyUtils.copy(bytes,target);
		return savefilename;	
	}

	public boolean login(MemberDTO member) {
		boolean result=mdao.login(member);
		if(result) {
			return true;
		}
		else {
			return false;
		}
	}

}
