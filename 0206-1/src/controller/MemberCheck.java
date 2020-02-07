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


@WebServlet("/Check")
public class MemberCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberCheck() {
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
		String name=member.getName();
		String email=member.getEmail();
		request.setAttribute("name", name);
		request.setAttribute("email", email); 
		if(result) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("ModifyMember.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("CheckFail.jsp");
		}
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
