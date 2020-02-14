package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.MemberSearchService;


@WebServlet("/SearchPassword")
public class MemberSearchPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberSearchPassword() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String email=request.getParameter("email");
		MemberSearchService service = new MemberSearchService();
		MemberDTO member = new MemberDTO();
		boolean result=service.memberSearchPassword(id,email,member);
		if(result) {
			request.setAttribute("password", member.getPassword());
			RequestDispatcher dispatcher = request.getRequestDispatcher("Member/PasswordOutput.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("Member/SearchFail.jsp");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}