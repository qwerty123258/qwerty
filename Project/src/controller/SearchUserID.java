package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UsersDTO;
import service.UserService;

@WebServlet("/SearchUserID")
public class SearchUserID extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SearchUserID() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String personno=request.getParameter("personno");
		UserService userservice = new UserService();
		UsersDTO user = new UsersDTO();
		boolean result=userservice.searchUserID(name,personno,user);
		if(result) {
			response.getWriter().write(user.getID());
		}
		else {
			response.getWriter().write("searchFail");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
