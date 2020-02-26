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


@WebServlet("/ModifyUser")
public class ModifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyUser() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		UserService userservice = new UserService();
    	UsersDTO user = new UsersDTO();
		userservice.modify(id,user);
		request.setAttribute("name", user.getName());
		String email[]=user.getEmail().split("@");
		request.setAttribute("email", email[0]);
		request.setAttribute("domain",email[1] );
        RequestDispatcher dispatcher = request.getRequestDispatcher("ModifyUser.jsp");
        dispatcher.forward(request, response);
		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
