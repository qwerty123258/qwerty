package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberDTO;
import service.MemberCheckService;
import service.MemberDeleteService;


@WebServlet("/DeleteCheck")
public class MemberDeleteCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDeleteCheck() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		String password=request.getParameter("password");
		MemberDTO member = new MemberDTO();
		MemberCheckService service=new MemberCheckService();
		boolean result=service.checkMember(id,password,member);
		if(result) {
			MemberDeleteService deleteservice = new MemberDeleteService();
			deleteservice.deleteMember(id);
			session.invalidate();
			response.sendRedirect("Member/MemberDelSuccess.jsp");
		}
		else {
			response.sendRedirect("Member/CheckFail.jsp");
		}
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}