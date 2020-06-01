package com.icia.airandroom.util;

import java.sql.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.icia.airandroom.dto.MemberDTO;
import com.icia.airandroom.service.MemberService;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	 
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private HttpSession session;
     
    // preHandle() : 컨트롤러보다 먼저 수행되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception { 
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("id");
        if ( obj ==null ){
            Cookie loginCookie = WebUtils.getCookie(request,"loginCookie");
            if ( loginCookie !=null ){
                String sessionId = loginCookie.getValue();
                MemberDTO member = memberService.checkUserWithSessionKey(sessionId);                 
                if ( member !=null ){
                	boolean result=memberService.checkperiod(member);
                	if(result) {
                		System.out.println("로그인 금지기간");
                        return true;	
                	}
                	else {
                		System.out.println("로그인 가능");
                        session.setAttribute("id", member.getId());
                        session.setAttribute("kind", member.getKind());
                        return true;
                	}
                }
            }
            return true;
        }
         
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView)throws Exception {
    	  String requestURI = request.getRequestURI();
    	  if(requestURI.equals("/airandroom/roomsRegForm") || requestURI.equals("/airandroom/roomsUpdateForm") || requestURI.equals("/airandroom/roomManagement")) {
    		  String kind=(String)session.getAttribute("kind");
    		  if(!kind.equals("room")) {
    			  modelAndView.setViewName("Fail");
    		  }
    	  }
    	  else if( requestURI.equals("/airandroom/modifyAirline") || requestURI.equals("/airandroom/goCreateAirline") || requestURI.equals("/airandroom/airlineManagement")) {
    		  String kind=(String)session.getAttribute("kind");
    		  if(!kind.equals("airline")) {
    			  modelAndView.setViewName("Fail");
    		  }
    	  }
    }
     
}
