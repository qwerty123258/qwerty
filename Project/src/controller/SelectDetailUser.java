package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UsersDTO;
import service.UserService;


@WebServlet("/SelectDetailUser")
public class SelectDetailUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectDetailUser() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		String admin=(String) session.getAttribute("id");
		String id=request.getParameter("id");
		if(admin==null) {
			UserService userservice = new UserService();
			UsersDTO user= new UsersDTO();
			userservice.detailUser(id,user);
			request.setAttribute("id", user.getID());
			request.setAttribute("name", user.getName());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("grade", user.getGrade());
			request.setAttribute("point", user.getPoint());
	        RequestDispatcher dispatcher = request.getRequestDispatcher("DetailUserForUser.jsp");
	        dispatcher.forward(request, response);
		}
		else if(admin.equals(id)) {
			UserService userservice = new UserService();
			UsersDTO user= new UsersDTO();
			userservice.detailUser(id,user);
			request.setAttribute("id", user.getID());
			request.setAttribute("pw", user.getPw());
			request.setAttribute("name", user.getName());
			request.setAttribute("personno", user.getPersonno());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("grade", user.getGrade());
			request.setAttribute("point", user.getPoint());
	        RequestDispatcher dispatcher = request.getRequestDispatcher("DetailUserForAdmin.jsp");
	        dispatcher.forward(request, response);
		}
		else {
			UserService userservice = new UserService();
			UsersDTO user= new UsersDTO();
			userservice.detailUser(id,user);
			request.setAttribute("id", user.getID());
			request.setAttribute("name", user.getName());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("grade", user.getGrade());
			request.setAttribute("point", user.getPoint());
	        RequestDispatcher dispatcher = request.getRequestDispatcher("DetailUserForUser.jsp");
	        dispatcher.forward(request, response);
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
