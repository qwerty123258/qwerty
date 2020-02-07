package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberBlackListCheck;
import service.MemberLoginService;

@WebServlet("/Login")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberLogin() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		MemberLoginService service= new MemberLoginService();
		MemberBlackListCheck check=new MemberBlackListCheck();
		boolean blacklist=check.blackListCheck(id);
		if(blacklist) {
			response.sendRedirect("BlackListLoginFail.jsp");
		}
		else {
			boolean result=service.memberLogin(id,password);
			if(result) {
				HttpSession session =request.getSession();
				session.setAttribute("id", id);
				response.sendRedirect("LoginMain.jsp");
				}
			else if(!result) {
				response.sendRedirect("LoginFail.jsp");
			}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
