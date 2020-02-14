package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberRemoveBlackListService;

@WebServlet("/RemoveBlackList")
public class MemberRemoveBlackList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberRemoveBlackList() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		MemberRemoveBlackListService service = new MemberRemoveBlackListService();
		boolean result=service.removeBlackList(id);
		if(result) {
			response.sendRedirect("Member/BlackListRemoveSuccess.jsp");
		}
		else {
			response.sendRedirect("Member/BlackListRemoveFail.jsp");
		}
		
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}