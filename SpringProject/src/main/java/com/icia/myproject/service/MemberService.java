package com.icia.myproject.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
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

	public MemberDTO myProfile(String id) {
		MemberDTO myProfile=mdao.myProfile(id);
		return myProfile;
		
	}

	public boolean deleteProfile(MemberDTO member) {
		String beforeimg=member.getBfprofileimg();
		System.out.println(beforeimg);
		String savePath="C:\\Users\\5\\git\\qwerty\\SpringProject\\src\\main\\webapp\\resources\\fileUpload\\"+beforeimg;
		File f = new File(savePath);
		 if(f.exists()){
		 f.delete(); 
		}
		int result=mdao.deleteProfile(member);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean updateProfile(MemberDTO member) throws IOException {
		MultipartFile mfile = member.getMfile();
		if(mfile.isEmpty()) {
			String beforeimg=member.getBfprofileimg();
			String beforeoriimg=member.getBforiprofileimg();
			member.setProfileimg(beforeimg);
			member.setOriprofileimg(beforeoriimg);
		}
		else if(!mfile.isEmpty()) {
			String fileoriname=mfile.getOriginalFilename();
			member.setOriprofileimg(fileoriname);
			String savefilename=upload(fileoriname,mfile.getBytes());
			member.setProfileimg(savefilename);
			String beforeimg=member.getBfprofileimg();
			String savePath="C:\\Users\\5\\git\\qwerty\\SpringProject\\src\\main\\webapp\\resources\\fileUpload\\"+beforeimg;
			File f = new File(savePath);
			 if(f.exists()){
			 f.delete(); 
			}
		}
		int result=mdao.updateProfile(member);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

	public ModelAndView selectMember() {
		mav=new ModelAndView();
		List<MemberDTO> memberList=mdao.selectMember();
		mav.addObject("memberList",memberList);
		mav.setViewName("member/Select");
		return mav;
	}

	public ModelAndView showDetail(String id) {
		mav=new ModelAndView();
		List<MemberDTO> memberList=mdao.showDetail(id);
		mav.addObject("memberList",memberList);
		mav.setViewName("member/ShowDetail");
		return mav;
	}

	public boolean adminDeleteMember(String id) {
		int result=mdao.adminDelete(id);
		if(result>0) {
			return true;
		}
		else {
			return false;
		}
	}

}
